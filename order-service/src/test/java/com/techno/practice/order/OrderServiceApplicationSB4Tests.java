/*
package com.techno.practice.order;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.techno.practice.order.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.mysql.MySQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest(httpPort = 8082)

class OrderServiceApplicationSB4Tests {

    static WireMockServer wireMockServer;

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
      InventoryClientStub.stubInventoryCall("SKU-ABC-124", 3);
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
*/
