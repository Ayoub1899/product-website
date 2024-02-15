package com.website.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * La classe Product représente une entité de produit.
 */
@Entity
@Data
@Document(collection = "Product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    /**
     * ID du produit (généré automatiquement).
     */
    @Id
    @GeneratedValue(generator = "productuuid")
    private String id;

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

    /**
     * Image du produit en format binaire.
     */
    private Binary image;

    /**
     * Méthode de comparaison pour vérifier l'égalité avec un autre objet.
     *
     * @param o L'objet avec lequel comparer.
     * @return true si les objets sont égaux, sinon false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name)
                && Objects.equals(price, product.price)
                && Objects.equals(quantity, product.quantity)
                && Objects.equals(category, product.category)
                && Objects.equals(image, product.image);
    }

    /**
     * Génère le code de hachage pour l'objet.
     *
     * @return Le code de hachage.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, category, image);
    }
}
