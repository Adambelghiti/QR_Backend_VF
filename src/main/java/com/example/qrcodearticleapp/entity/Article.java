package com.example.qrcodearticleapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @Column(name = "serial_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serialNumber;

    private String nom;
    private String longueur;
    private String largeur;
    private String hauteur;
    private String categorie;

    @Lob
    private byte[] codeQr;

    @JsonManagedReference("entrepot-articles")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "entrepot_id")
    private Entrepot entrepot;

    @JsonManagedReference("fabricant-articles")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fabricant_id")
    private Fabricant fabricant;

    @JsonManagedReference("fournisseur-articles")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;


    // Getters and Setters
    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLongueur() {
        return longueur;
    }

    public void setLongueur(String longueur) {
        this.longueur = longueur;
    }

    public String getLargeur() {
        return largeur;
    }

    public void setLargeur(String largeur) {
        this.largeur = largeur;
    }

    public String getHauteur() {
        return hauteur;
    }

    public void setHauteur(String hauteur) {
        this.hauteur = hauteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public byte[] getCodeQr() {
        return codeQr;
    }

    public void setCodeQr(byte[] codeQr) {
        this.codeQr = codeQr;
    }

    public Entrepot getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(Entrepot entrepot) {
        this.entrepot = entrepot;
    }

    public Fabricant getFabricant() {
        return fabricant;
    }

    public void setFabricant(Fabricant fabricant) {
        this.fabricant = fabricant;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Override
    public String toString() {
        return "Article{" +
                "serialNumber=" + serialNumber +
                ", nom='" + nom + '\'' +
                ", longueur=" + longueur +
                ", largeur=" + largeur +
                ", hauteur=" + hauteur +
                ", categorie='" + categorie + '\'' +
                ", entrepot=" + (entrepot != null ? entrepot.toString() : "N/A") +
                ", fabricant=" + (fabricant != null ? fabricant.toString() : "N/A") +
                ", fournisseur=" + (fournisseur != null ? fournisseur.toString() : "N/A") +
                ", codeQr=" + (codeQr != null ? "[QR Code Data]" : "N/A") +
                '}';
    }

}
