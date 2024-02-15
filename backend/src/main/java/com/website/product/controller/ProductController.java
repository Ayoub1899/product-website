package com.website.product.controller;

import com.website.product.model.Product;
import com.website.product.service.ProductService;
import com.website.product.service.RegisterRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Classe contrôleur pour gérer les opérations liées aux produits.
 */
@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private ProductService productService;

    /**
     * Récupérer tous les produits.
     *
     * @return Liste de tous les produits.
     */
    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    /**
     * Récupérer un produit par son ID.
     *
     * @param productId ID du produit.
     * @return Produit avec l'ID spécifié.
     */
    @GetMapping("/getById/{productId}")
    public Product getProductById(@PathVariable String productId) {
        return productService.findById(productId);
    }

    /**
     * Créer un nouveau produit.
     *
     * @param request Demande d'enregistrement du produit.
     * @param file    Fichier image du produit.
     * @throws IOException en cas d'erreur d'entrée/sortie.
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public void addProduct(@RequestPart(value = "data", name = "data") RegisterRequest request,
                           @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        productService.addProduct(request, file);
    }

    /**
     * Supprimer un produit par son ID.
     *
     * @param id       ID du produit à supprimer.
     * @param response HttpServletResponse pour gérer la réponse.
     */
    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable("productId") String id, HttpServletResponse response) {
        if (productService.findById(id) != null) {
            productService.deleteProduct(id);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setHeader("Error", "Ce produit n'existe pas");
        }
    }

    /**
     * Mettre à jour les informations d'un produit.
     *
     * @param id       ID du produit à mettre à jour.
     * @param request  Demande d'enregistrement du produit contenant les informations mises à jour.
     * @param file     Fichier image du produit mis à jour (optionnel).
     * @param response HttpServletResponse pour gérer la réponse.
     * @throws IOException en cas d'erreur d'entrée/sortie.
     */
    @PutMapping(value = "/updateInformation/{productId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public void updateProduct(@PathVariable("productId") String id,
                              @RequestPart(value = "data", name = "data") RegisterRequest request,
                              @RequestParam(value = "file", required = false) MultipartFile file,
                              HttpServletResponse response) throws IOException {
        if (productService.findById(id) != null) {
            productService.updateProductInformation(request, id, file);
            return;
        }
        response.setStatus(HttpStatus.NO_CONTENT.value());
        response.setHeader("Error", "Ce produit n'existe pas");
    }
}
