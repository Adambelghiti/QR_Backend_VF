package com.example.qrcodearticleapp.service;

import com.example.qrcodearticleapp.Dto.FournisseurDTO;
import com.example.qrcodearticleapp.entity.Fournisseur;
import com.example.qrcodearticleapp.repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FournisseurService {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<FournisseurDTO> getAllFournisseurs() {
        return fournisseurRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FournisseurDTO getFournisseurById(Long id) {
        return fournisseurRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public FournisseurDTO saveFournisseur(FournisseurDTO fournisseurDTO) {
        Fournisseur fournisseur = convertToEntity(fournisseurDTO);
        Fournisseur savedFournisseur = fournisseurRepository.save(fournisseur);
        return convertToDTO(savedFournisseur);
    }

    public void deleteFournisseur(Long id) {
        fournisseurRepository.deleteById(id);
    }

    private FournisseurDTO convertToDTO(Fournisseur fournisseur) {
        FournisseurDTO dto = new FournisseurDTO();
        dto.setId(fournisseur.getId());
        dto.setName(fournisseur.getName());
        return dto;
    }

    private Fournisseur convertToEntity(FournisseurDTO dto) {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(dto.getId());
        fournisseur.setName(dto.getName());
        return fournisseur;
    }
}
