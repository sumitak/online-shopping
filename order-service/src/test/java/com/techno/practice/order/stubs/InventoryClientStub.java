package com.techno.practice.order.stubs;

import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {



    public static void stubInventoryCall(String skuCode, int quantity) {
        // Implementation for stubbing the inventory call
        stubFor(WireMock.get(urlPathEqualTo("/api/inventory/isInStock"))
                .withQueryParam("skuCode", equalTo("SKU-ABC-124"))
                .withQueryParam("quantity", equalTo("3"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));

    }
}
