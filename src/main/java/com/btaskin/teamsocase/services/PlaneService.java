package com.btaskin.teamsocase.services;

import com.btaskin.teamsocase.domain.Plane;
import com.btaskin.teamsocase.repositories.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class PlaneService extends BaseService<Plane, Long, PlaneRepository> {

    @Autowired
    public PlaneService(PlaneRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected Class<Plane> getEntityClass() {
        return Plane.class;
    }
}
