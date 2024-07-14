package com.example.qrcodearticleapp.repository;

import com.example.qrcodearticleapp.entity.Entrepot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepotRepository extends JpaRepository<Entrepot, Long> {
}
