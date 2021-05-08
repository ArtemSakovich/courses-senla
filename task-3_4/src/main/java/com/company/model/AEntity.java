package com.company.model;

import javax.persistence.*;
@MappedSuperclass
public abstract class AEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
