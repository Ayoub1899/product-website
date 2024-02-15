package com.website.product.service;

import com.website.product.model.Product;
import com.website.product.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * La classe ProductService fournit des services liés à la gestion des produits.
 */
@AllArgsConstructor
@Service
public class ProductService {
    private ProductRepository productRepository;

    /**
     * Récupère tous les produits.
     *
     * @return Liste de tous les produits.
     */
    public List<Product> findAllProducts() {
        List<Product> products = productRepository.findAll();
        if (!products.isEmpty()) {
            return products;
        }
        return new ArrayList<>();
    }

    /**
     * Récupère un produit par son ID.
     *
     * @param id ID du produit.
     * @return Le produit avec l'ID spécifié.
     * @throws ResponseStatusException si le produit n'est pas trouvé.
     */
    public Product findById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce produit n'existe pas"));
    }

    /**
     * Ajoute un nouveau produit.
     *
     * @param request Demande d'enregistrement du produit.
     * @param file    Fichier image du produit.
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void addProduct(RegisterRequest request, MultipartFile file) throws IOException {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());
        product.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));

        productRepository.save(product);
    }

    /**
     * Met à jour les informations d'un produit existant.
     *
     * @param request Demande d'enregistrement du produit avec les informations mises à jour.
     * @param id      ID du produit à mettre à jour.
     * @param file    Fichier image du produit (optionnel).
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void updateProductInformation(RegisterRequest request, String id, MultipartFile file) throws IOException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product p = product.get();
            p.setName(request.getName());
            p.setPrice(request.getPrice());
            p.setQuantity(request.getQuantity());
            if (file != null) {
                p.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            }

            productRepository.save(p);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce produit n'existe pas");
        }
    }

    /**
     * Supprime un produit par son ID.
     *
     * @param id ID du produit à supprimer.
     */
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
