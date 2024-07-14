package com.example.qrcodearticleapp.entity;

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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "entrepot_id")
    private Entrepot entrepot;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fabricant_id")
    private Fabricant fabricant;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

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
    public void setFabricantId(Long fabricantId) {
        if (fabricantId != null) {
            Fabricant fabricant = new Fabricant();
            fabricant.setId(fabricantId);
            this.fabricant = fabricant;
        } else {
            this.fabricant = null;
        }
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
    public void setFournisseurId(Long fournisseurId) {
        if (fournisseurId != null) {
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(fournisseurId);
            this.fournisseur = fournisseur;
        } else {
            this.fournisseur = null;
        }
    }
    public void setEntrepotId(Long entrepotId) {
        if (entrepotId != null) {
            Entrepot entrepot = new Entrepot();
            entrepot.setId(entrepotId);
            this.entrepot = entrepot;
        } else {
            this.entrepot = null;
        }
    }

}