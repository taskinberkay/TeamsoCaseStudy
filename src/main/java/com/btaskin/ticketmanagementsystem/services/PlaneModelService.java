package com.btaskin.ticketmanagementsystem.services;

import com.btaskin.ticketmanagementsystem.domain.PlaneModel;
import com.btaskin.ticketmanagementsystem.repositories.PlaneModelRepository;
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
