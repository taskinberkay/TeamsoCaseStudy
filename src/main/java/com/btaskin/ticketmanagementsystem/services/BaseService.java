package com.btaskin.ticketmanagementsystem.services;

import com.btaskin.ticketmanagementsystem.dtos.PagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public abstract class BaseService<Entity, ID, Repository extends JpaRepository<Entity, ID>> {

    protected final Repository repository;
    protected final EntityManager entityManager;

    @Autowired
    public BaseService(Repository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    public List<Entity> findAll() {
        return repository.findAll();
    }

    public Entity findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public Entity save(Entity entity) {
        return repository.save(entity);
    }

    public void delete(Entity entity) {
        repository.delete(entity);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    public PagedResponse<Entity> findAllByParams(Map<String, Object> params) {
        // Pagination keys
        int start = (int) params.getOrDefault("startIndex", 0);
        int pageSize = (int) params.getOrDefault("pageSize", 10);

        // Remove pagination keys
        Map<String, Object> filteredParams = params.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("startIndex") && !entry.getKey().equals("pageSize"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // Main query
        CriteriaQuery<Entity> cq = cb.createQuery(getEntityClass());
        Root<Entity> root = cq.from(getEntityClass());
        List<Predicate> predicates = buildPredicates(root, cb, filteredParams);
        cq.where(predicates.toArray(new Predicate[0]));

        // Execute paginated query
        TypedQuery<Entity> query = entityManager.createQuery(cq)
                .setFirstResult(start);
        if (pageSize > 0) {
            query.setMaxResults(pageSize);
        }
        List<Entity> result = query.getResultList();

        // Count query
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Entity> countRoot = countQuery.from(getEntityClass());
        List<Predicate> countPredicates = buildPredicates(countRoot, cb, filteredParams);
        countQuery.select(cb.count(countRoot))
                .where(countPredicates.toArray(new Predicate[0]));
        long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        return new PagedResponse<>(totalCount, result);
    }

    private List<Predicate> buildPredicates(Root<Entity> root, CriteriaBuilder cb, Map<String, Object> filteredParams) {
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filteredParams.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (key.contains(".")) {
                // Handle nested properties
                String[] pathSegments = key.split("\\.");
                From<?, ?> from = root;

                // Traverse the relationship path
                for (int i = 0; i < pathSegments.length - 1; i++) {
                    from = from.join(pathSegments[i], JoinType.INNER);
                }

                // Add predicate for the final property
                Path<Object> propertyPath = from.get(pathSegments[pathSegments.length - 1]);
                predicates.add(cb.equal(propertyPath, value));
            } else {
                // Handle simple property
                predicates.add(cb.equal(root.get(key), value));
            }
        }

        return predicates;
    }

    protected abstract Class<Entity> getEntityClass();

}