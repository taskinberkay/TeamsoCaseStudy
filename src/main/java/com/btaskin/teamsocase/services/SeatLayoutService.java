package com.btaskin.teamsocase.services;

import com.btaskin.teamsocase.domain.SeatLayout;
import com.btaskin.teamsocase.repositories.SeatLayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class SeatLayoutService extends BaseService<SeatLayout, Long, SeatLayoutRepository> {

    @Autowired
    public SeatLayoutService(SeatLayoutRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected Class<SeatLayout> getEntityClass() {
        return SeatLayout.class;
    }
}
