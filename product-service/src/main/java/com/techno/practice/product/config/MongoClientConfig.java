package com.techno.practice.product.config;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.util.List;

@Configuration
public class MongoClientConfig {

    @Bean
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential.createScramSha256Credential(
                "appuser",
                "admin",
                "appPass123".toCharArray()
        );

        return MongoClients.create(
                com.mongodb.MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(List.of(new ServerAddress("localhost", 27017)))
                        )
                        .credential(credential)
                        .build()
        );
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, "product_service");
    }
}
