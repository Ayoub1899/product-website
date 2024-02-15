package com.website.product.repositories;

import com.website.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * L'interface ProductRepository étend l'interface MongoRepository pour les opérations CRUD sur les produits.
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    /**
     * Recherche un produit par son ID.
     *
     * @param id ID du produit.
     * @return Une option contenant le produit avec l'ID spécifié.
     */
    Optional<Product> findById(String id);
}
