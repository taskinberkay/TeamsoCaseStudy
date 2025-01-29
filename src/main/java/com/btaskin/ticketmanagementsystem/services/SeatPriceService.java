package com.btaskin.ticketmanagementsystem.services;

import com.btaskin.ticketmanagementsystem.domain.SeatPrice;
import com.btaskin.ticketmanagementsystem.enums.EnumSeatClass;
import com.btaskin.ticketmanagementsystem.repositories.SeatPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional(rollbackOn = Exception.class)
public class SeatPriceService extends BaseService<SeatPrice, Long, SeatPriceRepository> {

    @Autowired
    public SeatPriceService(SeatPriceRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected Class<SeatPrice> getEntityClass() {
        return SeatPrice.class;
    }

    public BigDecimal findPricePerUnitOfDistanceByGroupLetter(EnumSeatClass seatClass) {
        String hql = "select pricePerUnitOfDistance from SeatPrice where seatClass = :seatClass";
        return (BigDecimal) entityManager.createQuery(hql)
                .setParameter("seatClass", seatClass)
                .getSingleResult();
    }
}
