package com.example.qrcodearticleapp.controller;

import com.example.qrcodearticleapp.entity.Entrepot;
import com.example.qrcodearticleapp.service.EntrepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entrepots")
public class EntrepotController {

    @Autowired
    private EntrepotService entrepotService;

    @GetMapping
    public String getAllEntrepots(Model model) {
        List<Entrepot> entrepots = entrepotService.getAllEntrepots();
        model.addAttribute("entrepots", entrepots);
        return "entrepots";
    }

    @GetMapping("/new")
    public String createEntrepotForm(Model model) {
        model.addAttribute("entrepot", new Entrepot());
        return "entrepot-form";
    }

    @PostMapping
    public String saveEntrepot(@ModelAttribute Entrepot entrepot) {
        entrepotService.saveEntrepot(entrepot);
        return "redirect:/entrepots";
    }

    @GetMapping("/edit/{id}")
    public String editEntrepotForm(@PathVariable Long id, Model model) {
        Entrepot entrepot = entrepotService.getEntrepotById(id);
        model.addAttribute("entrepot", entrepot);
        return "entrepot-form";
    }

    @PostMapping("/{id}")
    public String updateEntrepot(@PathVariable Long id, @ModelAttribute Entrepot entrepot) {
        entrepot.setId(id);
        entrepotService.saveEntrepot(entrepot);
        return "redirect:/entrepots";
    }

    @GetMapping("/delete/{id}")
    public String deleteEntrepot(@PathVariable Long id) {
        entrepotService.deleteEntrepot(id);
        return "redirect:/entrepots";
    }
}
