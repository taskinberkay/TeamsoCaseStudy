package com.btaskin.ticketmanagementsystem.domain;

import com.btaskin.ticketmanagementsystem.enums.EnumSeatClass;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class SeatPrice {
    @SequenceGenerator(name = "SEAT_PRICE_ID_SEQ_GEN", sequenceName = "SEAT_PRICE_ID_SEQ")
    @GeneratedValue(generator = "SEAT_PRICE_ID_SEQ_GEN")
    @Id
    private Long id;

    @Version
    private Long version;

    @Column(length = 1, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EnumSeatClass seatClass;

   @Column(precision = 15, scale = 2)
   private BigDecimal pricePerUnitOfDistance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public EnumSeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(EnumSeatClass seatClass) {
        this.seatClass = seatClass;
    }

    public BigDecimal getPricePerUnitOfDistance() {
        return pricePerUnitOfDistance;
    }

    public void setPricePerUnitOfDistance(BigDecimal price) {
        this.pricePerUnitOfDistance = price;
    }
}
