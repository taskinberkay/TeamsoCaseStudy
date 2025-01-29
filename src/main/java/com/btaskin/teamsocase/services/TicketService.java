package com.btaskin.teamsocase.services;

import com.btaskin.teamsocase.domain.Ticket;
import com.btaskin.teamsocase.exceptions.SeatIsAlreadyPurchasedException;
import com.btaskin.teamsocase.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Map;

@Service
@Transactional(rollbackOn = Exception.class)
public class TicketService extends BaseService<Ticket, Long, TicketRepository> {


    @Autowired
    public TicketService(TicketRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected Class<Ticket> getEntityClass() {
        return Ticket.class;
    }


    public Ticket handlePurchase(Ticket ticket) throws InterruptedException {
        try {
            Thread.sleep(5000);
            if (findAllByParams(Map.of("seat", ticket.getSeat())).getTotalCount() > 0) {
                throw new SeatIsAlreadyPurchasedException("Seat is already purchased by another customer.");
            }
            return save(ticket);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
