package com.example.qrcodearticleapp.service;

import com.example.qrcodearticleapp.Dto.ArticleDTO;
import com.example.qrcodearticleapp.entity.Article;
import com.example.qrcodearticleapp.entity.Entrepot;
import com.example.qrcodearticleapp.entity.Fabricant;
import com.example.qrcodearticleapp.entity.Fournisseur;
import com.example.qrcodearticleapp.repository.ArticleRepository;
import com.example.qrcodearticleapp.repository.EntrepotRepository;
import com.example.qrcodearticleapp.repository.FabricantRepository;
import com.example.qrcodearticleapp.repository.FournisseurRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private EntrepotRepository entrepotRepository;

    @Autowired
    private FabricantRepository fabricantRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article createOrUpdateArticle(ArticleDTO articleDTO) {
        Article article = (articleDTO.getSerialNumber() != null) ?
                articleRepository.findById(articleDTO.getSerialNumber()).orElse(new Article()) :
                new Article();

        article.setNom(articleDTO.getNom());
        article.setLongueur(articleDTO.getLongueur());
        article.setLargeur(articleDTO.getLargeur());
        article.setHauteur(articleDTO.getHauteur());
        article.setCategorie(articleDTO.getCategorie());

        if (articleDTO.getEntrepotNom() != null) {
            Entrepot entrepot = entrepotRepository.findByNom(articleDTO.getEntrepotNom())
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Entrepot not found"));
            article.setEntrepot(entrepot);
        }

        if (articleDTO.getFabricantName() != null) {
            Fabricant fabricant = fabricantRepository.findByName(articleDTO.getFabricantName())
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Fabricant not found"));
            article.setFabricant(fabricant);
        }

        if (articleDTO.getFournisseurName() != null) {
            Fournisseur fournisseur = fournisseurRepository.findByName(articleDTO.getFournisseurName())
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Fournisseur not found"));
            article.setFournisseur(fournisseur);
        }

        // Print the Article object's attributes in the service layer
        System.out.println("Article in Service Layer: " + article);

        String qrContent = String.format("ID: %d, Name: %s, Length: %s, Width: %s, Height: %s, Category: %s, Warehouse: %s, Manufacturer: %s, Supplier: %s",
                article.getSerialNumber(),
                article.getNom(),
                article.getLongueur(),
                article.getLargeur(),
                article.getHauteur(),
                article.getCategorie(),
                article.getEntrepot() != null ? article.getEntrepot().getNom() : "N/A",
                article.getFabricant() != null ? article.getFabricant().getName() : "N/A",
                article.getFournisseur() != null ? article.getFournisseur().getName() : "N/A"
        );
        article.setCodeQr(generateQRCode(qrContent));

        return saveArticle(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    private byte[] generateQRCode(String content) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return byteArrayOutputStream.toByteArray();
    }
}
