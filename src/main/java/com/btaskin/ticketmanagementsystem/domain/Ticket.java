package com.btaskin.ticketmanagementsystem.domain;

import javax.persistence.*;

@Entity
public class Ticket {
    @SequenceGenerator(name = "TICKET_ID_SEQ_GEN", sequenceName = "TICKET_ID_SEQ")
    @GeneratedValue(generator = "TICKET_ID_SEQ_GEN")
    @Id
    private Long id;

    @Version
    private Long version;

    @Column(unique = true, length = 10)
    private String ticketNumber;

    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    // unique = true is one of the precautions that will stop the same seat to be purchased by multiple users.
    private Seat seat;

    @Column(nullable = false, length = 50)
    private String passangerName;

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

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public String getPassangerName() {
        return passangerName;
    }

    public void setPassangerName(String passangerName) {
        this.passangerName = passangerName;
    }
}
