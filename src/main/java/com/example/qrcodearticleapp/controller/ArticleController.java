package com.example.qrcodearticleapp.controller;

import com.example.qrcodearticleapp.entity.Article;
import com.example.qrcodearticleapp.entity.Entrepot;
import com.example.qrcodearticleapp.service.ArticleService;
import com.example.qrcodearticleapp.service.EntrepotService;
import com.example.qrcodearticleapp.service.FabricantService;
import com.example.qrcodearticleapp.service.FournisseurService;
import com.example.qrcodearticleapp.service.QRCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private EntrepotService entrepotService;

    @Autowired
    private FabricantService fabricantService;

    @Autowired
    private FournisseurService fournisseurService;

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        if (article != null) {
            return ResponseEntity.ok(article);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Article> saveArticle(@RequestBody Article article) {
        // Save the article
        if (article.getEntrepot() != null && article.getEntrepot().getId() != null) {
            Entrepot entrepot = entrepotService.getEntrepotById(article.getEntrepot().getId());
            article.setEntrepot(entrepot);
        }

        // Generate QR code with selected fields
        StringBuilder qrContent = new StringBuilder();
        qrContent.append("Name: ").append(article.getNom()).append(", ");
        qrContent.append("Length: ").append(article.getLongueur()).append(", ");
        qrContent.append("Width: ").append(article.getLargeur()).append(", ");
        qrContent.append("Height: ").append(article.getHauteur()).append(", ");
        qrContent.append("Category: ").append(article.getCategorie()).append(", ");

        article.setCodeQr(generateQRCode(qrContent.toString()));
        Article savedArticle = articleService.saveArticle(article);
        return ResponseEntity.ok(savedArticle);
    }

    private byte[] generateQRCode(String text) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        article.setSerialNumber(id);
        if (article.getEntrepot() != null && article.getEntrepot().getId() != null) {
            Entrepot entrepot = entrepotService.getEntrepotById(article.getEntrepot().getId());
            article.setEntrepot(entrepot);
        }
        Article updatedArticle = articleService.saveArticle(article);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadQRCode(@PathVariable Long id) throws IOException {
        Article article = articleService.getArticleById(id);
        if (article != null && article.getCodeQr() != null) {
            Path path = Paths.get(System.getProperty("user.home"), "Desktop", "Codes");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Path filePath = path.resolve("QR_Code_" + id + ".png");
            try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
                fos.write(article.getCodeQr());
            }

            ByteArrayInputStream bis = new ByteArrayInputStream(article.getCodeQr());
            InputStreamResource resource = new InputStreamResource(bis);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filePath.getFileName().toString())
                    .contentType(MediaType.IMAGE_PNG)
                    .contentLength(article.getCodeQr().length)
                    .body(resource);
        }
        return ResponseEntity.notFound().build();
    }
}
