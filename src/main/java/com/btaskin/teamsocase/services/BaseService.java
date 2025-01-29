package com.btaskin.teamsocase.services;

import com.btaskin.teamsocase.dtos.PagedResponse;
import com.btaskin.teamsocase.repositories.PlaneModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        int pageSize = (int) params.getOrDefault("pageSize", 0);

        // Remove pagination keys from the rest
        Map<String, Object> filteredParams = params.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("startIndex") && !entry.getKey().equals("pageSize"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entity> cq = cb.createQuery(getEntityClass());
        Root<Entity> root = cq.from(getEntityClass());

        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : filteredParams.entrySet()) {
            predicates.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        // Execute paginated query
        TypedQuery<Entity> query = entityManager.createQuery(cq)
                .setFirstResult(start);
        if (pageSize > 0) {
            query.setMaxResults(pageSize);
        }
        List<Entity> result = query.getResultList();

        // Get total count (without pagination)
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Entity> countRoot = countQuery.from(getEntityClass());
        countQuery.select(cb.count(countRoot)).where(predicates.toArray(new Predicate[0]));
        long totalCount = entityManager.createQuery(countQuery).getSingleResult();

        return new PagedResponse<>(totalCount, result);
    }

    protected abstract Class<Entity> getEntityClass();

}