package com.example.qrcodearticleapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Fabricant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "fabricant")
    private List<Article> articles;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    // Getters and Setters
}
