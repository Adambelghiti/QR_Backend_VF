package com.example.qrcodearticleapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Entrepot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String location;

    @OneToMany(mappedBy = "entrepot")
    private List<Article> articles;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
// Other Getters and Setters
}
