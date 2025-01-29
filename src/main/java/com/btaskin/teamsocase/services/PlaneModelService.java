package com.btaskin.teamsocase.services;

import com.btaskin.teamsocase.domain.PlaneModel;
import com.btaskin.teamsocase.repositories.PlaneModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class PlaneModelService extends BaseService<PlaneModel, Long, PlaneModelRepository> {

    @Autowired
    public PlaneModelService(PlaneModelRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected Class<PlaneModel> getEntityClass() {
        return PlaneModel.class;
    }
}
