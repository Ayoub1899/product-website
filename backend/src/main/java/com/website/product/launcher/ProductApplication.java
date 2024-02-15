package com.website.product.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * La classe ProductApplication est la classe principale qui lance l'application Spring Boot.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableMongoRepositories("com.website.product.repositories")
@ComponentScan("com.website.product")
@EntityScan("com.website.product.model")
public class ProductApplication {

	/**
	 * Point d'entrée principal de l'application.
	 *
	 * @param args Les arguments de la ligne de commande.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
}
