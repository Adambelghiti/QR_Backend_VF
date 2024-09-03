package com.example.qrcodearticleapp.controller;

import com.example.qrcodearticleapp.Dto.FabricantDTO;
import com.example.qrcodearticleapp.service.FabricantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fabricants")
public class FabricantController {

    @Autowired
    private FabricantService fabricantService;

    @GetMapping
    public List<FabricantDTO> getAllFabricants() {
        return fabricantService.getAllFabricants();
    }

    @GetMapping("/{id}")
    public FabricantDTO getFabricantById(@PathVariable Long id) {
        return fabricantService.getFabricantById(id);
    }

    @PostMapping
    public FabricantDTO saveFabricant(@RequestBody FabricantDTO fabricantDTO) {
        return fabricantService.saveFabricant(fabricantDTO);
    }

    @PutMapping("/{id}")
    public FabricantDTO updateFabricant(@PathVariable Long id, @RequestBody FabricantDTO fabricantDTO) {
        fabricantDTO.setId(id);
        return fabricantService.saveFabricant(fabricantDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFabricant(@PathVariable Long id) {
        fabricantService.deleteFabricant(id);
    }
}
