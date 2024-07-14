package com.example.qrcodearticleapp.repository;

import com.example.qrcodearticleapp.entity.Fabricant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricantRepository extends JpaRepository<Fabricant, Long> {
}
