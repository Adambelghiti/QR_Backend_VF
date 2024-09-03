package com.example.qrcodearticleapp.service;

import com.example.qrcodearticleapp.Dto.EntrepotDTO;
import com.example.qrcodearticleapp.entity.Entrepot;
import com.example.qrcodearticleapp.repository.EntrepotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrepotService {

    @Autowired
    private EntrepotRepository entrepotRepository;

    public List<EntrepotDTO> getAllEntrepots() {
        return entrepotRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EntrepotDTO getEntrepotById(Long id) {
        return entrepotRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public EntrepotDTO saveEntrepot(EntrepotDTO entrepotDTO) {
        Entrepot entrepot = convertToEntity(entrepotDTO);
        Entrepot savedEntrepot = entrepotRepository.save(entrepot);
        return convertToDTO(savedEntrepot);
    }

    public void deleteEntrepot(Long id) {
        entrepotRepository.deleteById(id);
    }

    private EntrepotDTO convertToDTO(Entrepot entrepot) {
        EntrepotDTO dto = new EntrepotDTO();
        dto.setId(entrepot.getId());
        dto.setNom(entrepot.getNom());
        dto.setLocation(entrepot.getLocation()); // Correctly map the location field
        return dto;
    }

    private Entrepot convertToEntity(EntrepotDTO dto) {
        Entrepot entrepot = new Entrepot();
        entrepot.setId(dto.getId());
        entrepot.setNom(dto.getNom());
        entrepot.setLocation(dto.getLocation());
        return entrepot;
    }
}
