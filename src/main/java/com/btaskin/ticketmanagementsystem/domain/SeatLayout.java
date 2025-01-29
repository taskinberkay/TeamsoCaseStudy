package com.btaskin.ticketmanagementsystem.domain;

import javax.persistence.*;

@Entity
public class SeatLayout {
    @SequenceGenerator(name = "SEAT_LAYOUT_ID_SEQ_GEN", sequenceName = "SEAT_LAYOUT_ID_SEQ")
    @GeneratedValue(generator = "SEAT_LAYOUT_ID_SEQ_GEN")
    @Id
    private Long id;

    @Version
    private Long version;

    @Column(length = 30, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PlaneModel planeModel;

    /*  Format for layout:
    /   First digit stands for how many layout groups exist within the plane. (VIP, BUSINESS, ECO etc.) (g)
    /   Next digit stands for number of clusters within the first layout group. (n)
    /   Next n digit implies how many seats in each cluster. First one being the left most and last one being right most cluster. First and last seats of them respectively being window side seats.
    /   The next digit holds the information of how many digits after it belongs to the row count of this group. (r)
    /   Next r digit stands for the row count for the first group
    /   Last n + r + 2 digits repeat g times.
    */
    @Column(nullable = false, length = 30)
    private String layout;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(PlaneModel planeModel) {
        this.planeModel = planeModel;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }
}
