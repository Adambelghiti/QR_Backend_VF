package com.example.qrcodearticleapp.service;

import com.example.qrcodearticleapp.entity.Fabricant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class FabricantService {

    @Autowired
    private EntityManager entityManager;

    public List<Fabricant> getAllFabricants() {
        return entityManager.createQuery("SELECT f FROM Fabricant f", Fabricant.class).getResultList();
    }

    public Fabricant getFabricantById(Long id) {
        return entityManager.find(Fabricant.class, id);
    }

    public Fabricant save(Fabricant fabricant) {
        return entityManager.merge(fabricant);
    }

    public void deleteFabricant(Long id) {
        Fabricant fabricant = entityManager.find(Fabricant.class, id);
        if (fabricant != null) {
            entityManager.remove(fabricant);
        }
    }
}