package com.example.qrcodearticleapp.controller;

import com.example.qrcodearticleapp.entity.Fournisseur;
import com.example.qrcodearticleapp.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping
    public String getAllFournisseurs(Model model) {
        List<Fournisseur> fournisseurs = fournisseurService.getAllFournisseurs();
        model.addAttribute("fournisseurs", fournisseurs);
        return "fournisseurs";
    }

    @GetMapping("/new")
    public String createFournisseurForm(Model model) {
        model.addAttribute("fournisseur", new Fournisseur());
        return "fournisseur-form";
    }

    @PostMapping
    public String saveFournisseur(@ModelAttribute Fournisseur fournisseur) {
        fournisseurService.saveFournisseur(fournisseur);
        return "redirect:/fournisseurs";
    }

    @GetMapping("/edit/{id}")
    public String editFournisseurForm(@PathVariable Long id, Model model) {
        Fournisseur fournisseur = fournisseurService.getFournisseurById(id);
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseur-form";
    }

    @PostMapping("/{id}")
    public String updateFournisseur(@PathVariable Long id, @ModelAttribute Fournisseur fournisseur) {
        fournisseur.setId(id);
        fournisseurService.saveFournisseur(fournisseur);
        return "redirect:/fournisseurs";
    }

    @GetMapping("/delete/{id}")
    public String deleteFournisseur(@PathVariable Long id) {
        fournisseurService.deleteFournisseur(id);
        return "redirect:/fournisseurs";
    }
}
