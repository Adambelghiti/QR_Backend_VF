package com.example.qrcodearticleapp.controller;

import com.example.qrcodearticleapp.entity.Fabricant;
import com.example.qrcodearticleapp.service.FabricantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fabricants")
public class FabricantController {

    @Autowired
    private FabricantService fabricantService;

    @GetMapping
    public String getAllFabricants(Model model) {
        List<Fabricant> fabricants = fabricantService.getAllFabricants();
        model.addAttribute("fabricants", fabricants);
        return "fabricants";
    }

    @GetMapping("/new")
    public String createFabricantForm(Model model) {
        model.addAttribute("fabricant", new Fabricant());
        return "fabricant-form";
    }

    @PostMapping
    public String saveFabricant(@ModelAttribute Fabricant fabricant) {
        fabricantService.saveFabricant(fabricant);
        return "redirect:/fabricants";
    }

    @GetMapping("/edit/{id}")
    public String editFabricantForm(@PathVariable Long id, Model model) {
        Fabricant fabricant = fabricantService.getFabricantById(id);
        model.addAttribute("fabricant", fabricant);
        return "fabricant-form";
    }

    @PostMapping("/{id}")
    public String updateFabricant(@PathVariable Long id, @ModelAttribute Fabricant fabricant) {
        fabricant.setId(id);
        fabricantService.saveFabricant(fabricant);
        return "redirect:/fabricants";
    }

    @GetMapping("/delete/{id}")
    public String deleteFabricant(@PathVariable Long id) {
        fabricantService.deleteFabricant(id);
        return "redirect:/fabricants";
    }
}
