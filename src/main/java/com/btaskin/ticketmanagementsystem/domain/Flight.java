package com.btaskin.ticketmanagementsystem.domain;

import com.btaskin.ticketmanagementsystem.enums.EnumFlightStatus;

import javax.persistence.*;
import java.math.BigDecimal;
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
    @JoinColumn(nullable = false, updatable = false)
    private Plane plane;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal distance;

    @Column(length = 500)
    private String description;

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

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
