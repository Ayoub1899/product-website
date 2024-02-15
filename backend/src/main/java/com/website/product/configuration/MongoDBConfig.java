package com.website.product.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

/**
 * La classe MongoDBConfig est une configuration Spring qui désactive le mappage du type Mongo par défaut.
 * Cela est nécessaire pour éviter l'enregistrement du type de classe dans la base de données MongoDB.
 */
@Configuration
public class MongoDBConfig implements InitializingBean {

    /**
     * Convertisseur MongoDB pour la gestion des types.
     */
    @Autowired
    @Lazy
    private MappingMongoConverter mappingMongoConverter;

    /**
     * Configure le convertisseur MongoDB après l'injection des dépendances.
     */
    @Override
    public void afterPropertiesSet() {
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }
}
