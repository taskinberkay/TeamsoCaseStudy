package com.btaskin.ticketmanagementsystem.domain;

import javax.persistence.*;

@Entity
public class PlaneModel {
    @SequenceGenerator(name = "PLANE_MODEL_ID_SEQ_GEN", sequenceName = "PLANE_MODEL_ID_SEQ")
    @GeneratedValue(generator = "PLANE_MODEL_ID_SEQ_GEN")
    @Id
    private Long id;

    @Version
    private Long version;

    @Column(unique = true, length = 20, nullable = false)
    private String name;

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
}
