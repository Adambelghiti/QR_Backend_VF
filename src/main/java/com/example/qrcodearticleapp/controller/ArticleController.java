package com.example.qrcodearticleapp.controller;

import com.example.qrcodearticleapp.entity.Article;
import com.example.qrcodearticleapp.service.ArticleService;
import com.example.qrcodearticleapp.service.EntrepotService;
import com.example.qrcodearticleapp.service.FabricantService;
import com.example.qrcodearticleapp.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private EntrepotService entrepotService;

    @Autowired
    private FabricantService fabricantService;

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping
    public String getAllArticles(Model model) {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "articles";
    }

    @GetMapping("/{id}")
    public String getArticleById(@PathVariable Long id, Model model) {
        Article article = articleService.getArticleById(id);
        if (article.getCodeQr() != null) {
            String encodedQrCode = Base64Utils.encodeToString(article.getCodeQr());
            model.addAttribute("encodedQrCode", encodedQrCode);
        }
        model.addAttribute("article", article);
        return "article";
    }

    @GetMapping("/new")
    public String createArticleForm(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("entrepots", entrepotService.getAllEntrepots());
        model.addAttribute("fabricants", fabricantService.getAllFabricants());
        model.addAttribute("fournisseurs", fournisseurService.getAllFournisseurs());
        return "article-form";
    }

    @PostMapping
    public String saveArticle(@ModelAttribute Article article) {
        // Save the article
        articleService.saveArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("/edit/{id}")
    public String editArticleForm(@PathVariable Long id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        model.addAttribute("entrepots", entrepotService.getAllEntrepots());
        model.addAttribute("fabricants", fabricantService.getAllFabricants());
        model.addAttribute("fournisseurs", fournisseurService.getAllFournisseurs());
        return "article-form";
    }

    @PostMapping("/{id}")
    public String updateArticle(@PathVariable Long id, @ModelAttribute Article article) {
        article.setSerialNumber(id);
        articleService.saveArticle(article);
        return "redirect:/articles";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/articles";
    }
}
