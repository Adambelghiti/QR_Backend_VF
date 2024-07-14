package com.example.qrcodearticleapp.service;

import com.example.qrcodearticleapp.entity.Fabricant;
import com.example.qrcodearticleapp.repository.FabricantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FabricantService {

    @Autowired
    private FabricantRepository fabricantRepository;

    public List<Fabricant> getAllFabricants() {
        return fabricantRepository.findAll();
    }

    public Fabricant getFabricantById(Long id) {
        return fabricantRepository.findById(id).orElse(null);
    }

    public Fabricant saveFabricant(Fabricant fabricant) {
        return fabricantRepository.save(fabricant);
    }

    public void deleteFabricant(Long id) {
        fabricantRepository.deleteById(id);
    }
}
