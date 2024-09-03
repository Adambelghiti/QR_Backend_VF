package com.example.qrcodearticleapp.controller;
import com.example.qrcodearticleapp.Dto.ArticleDTO;
import com.example.qrcodearticleapp.entity.Article;
import com.example.qrcodearticleapp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping
    public Article createOrUpdateArticle(@RequestBody ArticleDTO articleDTO) {
        Article article = articleService.createOrUpdateArticle(articleDTO);

        // Print the Article object's attributes in the controller layer
        System.out.println("Article in Controller Layer: " + article);

        return article;
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}
