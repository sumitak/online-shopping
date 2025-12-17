package com.techno.practice.order;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.mysql.MySQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

    @ServiceConnection
    static MySQLContainer mysqlContainer = new MySQLContainer("mysql:8.0.33");

    @LocalServerPort
    private Integer port;

    static{
        mysqlContainer.start();
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

	@Test
	void shouldCreateOrder() {
        String orderRequest = """
                {
                     "skuCode": "SKU-ABC-124",
                     "price": 1499.99,
                     "quantity": 3
                   }
                """;

      var responseBodyString =  RestAssured.given()
                .header("Content-Type", "application/json")
                .body(orderRequest)
                .when()
                .post("/api/orders")
                .then()
                .statusCode(201)
                .extract()
                .body().asString();

        assertThat(responseBodyString, Matchers.is("Order Placed Successfully"));
    }

}
