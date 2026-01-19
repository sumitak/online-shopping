package com.techno.practice.order.config;

import com.techno.practice.order.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * Configuration class for setting up the Inventory REST client.
 * Binding the InventoryClient interface to the remote Inventory service
 * using HttpExchange interface proxy.
 */
@Configuration
public class InventoryRestClientConfig {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    @Bean
    public InventoryClient invemtoryClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(inventoryServiceUrl)
                .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpserviceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpserviceProxyFactory.createClient(InventoryClient.class);
    }

}
