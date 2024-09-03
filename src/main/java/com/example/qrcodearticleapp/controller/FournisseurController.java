package com.example.qrcodearticleapp.controller;

import com.example.qrcodearticleapp.Dto.FournisseurDTO;
import com.example.qrcodearticleapp.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping
    public List<FournisseurDTO> getAllFournisseurs() {
        return fournisseurService.getAllFournisseurs();
    }

    @GetMapping("/{id}")
    public FournisseurDTO getFournisseurById(@PathVariable Long id) {
        return fournisseurService.getFournisseurById(id);
    }

    @PostMapping
    public FournisseurDTO saveFournisseur(@RequestBody FournisseurDTO fournisseurDTO) {
        return fournisseurService.saveFournisseur(fournisseurDTO);
    }

    @PutMapping("/{id}")
    public FournisseurDTO updateFournisseur(@PathVariable Long id, @RequestBody FournisseurDTO fournisseurDTO) {
        fournisseurDTO.setId(id);
        return fournisseurService.saveFournisseur(fournisseurDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFournisseur(@PathVariable Long id) {
        fournisseurService.deleteFournisseur(id);
    }
}
