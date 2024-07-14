package com.example.qrcodearticleapp.repository;

import com.example.qrcodearticleapp.entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
}
