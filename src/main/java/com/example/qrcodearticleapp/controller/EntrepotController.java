package com.example.qrcodearticleapp.controller;

import com.example.qrcodearticleapp.Dto.EntrepotDTO;
import com.example.qrcodearticleapp.service.EntrepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrepots")
public class EntrepotController {

    @Autowired
    private EntrepotService entrepotService;

    @GetMapping
    public List<EntrepotDTO> getAllEntrepots() {
        return entrepotService.getAllEntrepots();
    }

    @GetMapping("/{id}")
    public EntrepotDTO getEntrepotById(@PathVariable Long id) {
        return entrepotService.getEntrepotById(id);
    }

    @PostMapping
    public EntrepotDTO saveEntrepot(@RequestBody EntrepotDTO entrepotDTO) {
        return entrepotService.saveEntrepot(entrepotDTO);
    }

    @PutMapping("/{id}")
    public EntrepotDTO updateEntrepot(@PathVariable Long id, @RequestBody EntrepotDTO entrepotDTO) {
        entrepotDTO.setId(id);
        return entrepotService.saveEntrepot(entrepotDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEntrepot(@PathVariable Long id) {
        entrepotService.deleteEntrepot(id);
    }
}
