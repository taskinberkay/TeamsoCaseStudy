package com.btaskin.ticketmanagementsystem.services;

import com.btaskin.ticketmanagementsystem.domain.SeatLayout;
import com.btaskin.ticketmanagementsystem.repositories.SeatLayoutRepository;
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
