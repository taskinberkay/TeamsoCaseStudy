package com.btaskin.ticketmanagementsystem.domain;

import com.btaskin.ticketmanagementsystem.enums.EnumSeatClass;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Seat {
    @SequenceGenerator(name = "SEAT_ID_SEQ_GEN", sequenceName = "SEAT_ID_SEQ")
    @GeneratedValue(generator = "SEAT_ID_SEQ_GEN")
    @Id
    private Long id;

    @Version
    private Long version;

    @Column
    private String seatNumber;

    @ManyToOne
    @JoinColumn
    private Flight flight;

    @Column(precision = 15, scale = 2)
    private BigDecimal price;

    @Column(length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumSeatClass seatClass;

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

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public EnumSeatClass getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(EnumSeatClass seatClass) {
        this.seatClass = seatClass;
    }
}
