package com.techno.practice.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
/*import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;*/

@Configuration
public class Routes {

   /* @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product-service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.forward("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return GatewayRouterFunctions.route("order-service")
                .route(RequestPredicates.path("/api/order"), HandlerFunctions.forward("http://localhost:8082/"))
                .build();
    }*/

    @Bean
    public RouteLocator productServiceRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/product") // Matches requests to /get
                        .uri("http://localhost:8080")) // Routes to this URI
                .build();
    }

    @Bean
    public RouteLocator orderServiceRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/orders") // Matches requests to /get
                        .uri("http://localhost:8081")) // Routes to this URI
                .build();
    }

    @Bean
    public RouteLocator inventoryServiceRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/inventory/isInStock") // Matches requests to /get
                        .uri("http://localhost:8082")) // Routes to this URI
                .build();
    }

}
