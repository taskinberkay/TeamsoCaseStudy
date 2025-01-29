package com.btaskin.teamsocase.domain;

import com.btaskin.teamsocase.enums.EnumFlightStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Flight {
    @SequenceGenerator(name = "FLIGHT_ID_SEQ_GEN", sequenceName = "FLIGHT_ID_SEQ")
    @GeneratedValue(generator = "FLIGHT_ID_SEQ_GEN")
    @Id
    private Long id;

    @Version
    private Long version;

    @Column(nullable = false, unique = true, length = 4)
    private String flightNumber;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumFlightStatus flightStatus = EnumFlightStatus.SCHEDULED;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Plane plane;

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

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public EnumFlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(EnumFlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
