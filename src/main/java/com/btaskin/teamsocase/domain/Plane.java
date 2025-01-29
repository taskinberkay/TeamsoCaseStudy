package com.btaskin.teamsocase.domain;

import javax.persistence.*;

@Entity
public class Plane {
    @SequenceGenerator(name = "PLANE_ID_SEQ_GEN", sequenceName = "PLANE_ID_SEQ")
    @GeneratedValue(generator = "PLANE_ID_SEQ_GEN")
    @Id
    private Long id;

    @Version
    private Long version;

    @Column(unique = true, length = 10)
    private String tailNumber;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PlaneModel planeModel;

    @ManyToOne
    @JoinColumn(nullable = false)
    private SeatLayout seatLayout;

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

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(PlaneModel planeModel) {
        this.planeModel = planeModel;
    }

    public SeatLayout getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(SeatLayout seatLayout) {
        this.seatLayout = seatLayout;
    }
}
