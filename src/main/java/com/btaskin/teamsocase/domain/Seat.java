package com.btaskin.teamsocase.domain;

import javax.persistence.*;

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
}
