package com.website.product.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La classe RegisterRequest représente une demande d'enregistrement de produit.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    /**
     * Nom du produit.
     */
    private String name;

    /**
     * Prix du produit.
     */
    private Double price;

    /**
     * Quantité du produit.
     */
    private Integer quantity;

    /**
     * Catégorie du produit.
     */
    private String category;
}
