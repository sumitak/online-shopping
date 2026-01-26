package com.techno.practice.product;

import com.techno.practice.product.config.MongoClientConfig;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.hamcrest.Matchers;

/**
 * TestcontainersConfiguration is imported to provide the MongoDBContainer for testing.
 * This class contains integration tests for the Product Service application.
 */
//@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
// it will start the application context with a random port and include the Testcontainers configuration for MongoDB
//give me code to exclude MongoClientConfig.class from the context

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ProductServiceApplication.class
       )
class ProductServiceApplicationTests {

    /**
     * The MongoDBContainer instance managed by Testcontainers.
     * ServiceConnection annotation ensures that Spring Boot connects to this container for MongoDB operations.
     */
   /* @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");*/

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

	@Test
	void shouldCreateProduct() {
        String productRequest = """
                {
                      "name":"Java25",
                      "price":401,
                      "description": "First Ahead Book1"
                  }
                """;

        RestAssured.given()
                .header("Content-Type", "application/json")
                .body(productRequest)
                .when()
                .post("/api/product")
                .then()
                .statusCode(201)
                .body("id",  Matchers.notNullValue())
                .body("name", Matchers.equalTo("Java25"))
                .body("description",  Matchers.equalTo("First Ahead Book1"))
                .body("price",  Matchers.equalTo(401));
    }



}
