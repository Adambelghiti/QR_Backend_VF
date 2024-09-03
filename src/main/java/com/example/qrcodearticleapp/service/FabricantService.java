package com.example.qrcodearticleapp.service;

import com.example.qrcodearticleapp.Dto.FabricantDTO;
import com.example.qrcodearticleapp.entity.Fabricant;
import com.example.qrcodearticleapp.repository.FabricantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FabricantService {

    @Autowired
    private FabricantRepository fabricantRepository;

    public List<FabricantDTO> getAllFabricants() {
        return fabricantRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FabricantDTO getFabricantById(Long id) {
        Fabricant fabricant = fabricantRepository.findById(id).orElse(null);
        return fabricant != null ? convertToDTO(fabricant) : null;
    }

    public FabricantDTO saveFabricant(FabricantDTO fabricantDTO) {
        Fabricant fabricant = convertToEntity(fabricantDTO);
        return convertToDTO(fabricantRepository.save(fabricant));
    }

    public void deleteFabricant(Long id) {
        fabricantRepository.deleteById(id);
    }

    public FabricantDTO getFabricantByName(String name) {
        Optional<Fabricant> fabricant = fabricantRepository.findByName(name);
        return fabricant.isPresent() ? convertToDTO(fabricant.orElse(null)) : null;
    }

    private FabricantDTO convertToDTO(Fabricant fabricant) {
        return new FabricantDTO(fabricant.getId(), fabricant.getName());
    }

    private Fabricant convertToEntity(FabricantDTO fabricantDTO) {
        Fabricant fabricant = new Fabricant();
        fabricant.setId(fabricantDTO.getId());
        fabricant.setName(fabricantDTO.getName());
        return fabricant;
    }
}
