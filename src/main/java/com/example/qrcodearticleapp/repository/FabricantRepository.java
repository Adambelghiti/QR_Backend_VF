package com.example.qrcodearticleapp.repository;

import com.example.qrcodearticleapp.entity.Entrepot;
import com.example.qrcodearticleapp.entity.Fabricant;
import com.example.qrcodearticleapp.entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FabricantRepository extends JpaRepository<Fabricant, Long> {
     //Fabricant findByName(String name, List<Fabricant> fabricants);
     Optional<Fabricant> findByName(String name);
}
