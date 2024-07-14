package com.example.qrcodearticleapp.service;

import com.example.qrcodearticleapp.entity.Article;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
        // Ensure related entities are saved or fetched from the database
        if (article.getEntrepot() != null && article.getEntrepot().getId() != null) {
            article.setEntrepot(entrepotRepository.findById(article.getEntrepot().getId()).orElse(null));
        }
        if (article.getFabricant() != null && article.getFabricant().getId() != null) {
            article.setFabricant(fabricantRepository.findById(article.getFabricant().getId()).orElse(null));
        }
        if (article.getFournisseur() != null && article.getFournisseur().getId() != null) {
            article.setFournisseur(fournisseurRepository.findById(article.getFournisseur().getId()).orElse(null));
        }

        // Generate QR code with full information
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
        return articleRepository.save(article);
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
