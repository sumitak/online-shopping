package com.techno.practice.product.service;

import com.techno.practice.product.dto.ProductRequest;
import com.techno.practice.product.dto.ProductResponse;
import com.techno.practice.product.model.Product;
import com.techno.practice.product.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
/**
* Alternative way to use @RequiredArgsConstructor with @Autowired
 * This annotation generates a constructor with required arguments (final fields)
 * and applies the @Autowired annotation to it for dependency injection.
 * This is useful in Spring applications to automatically wire dependencies.
 * By using this approach, you can ensure that your service class is properly instantiated
 * with all its required dependencies injected by the Spring framework.
 * This helps in maintaining immutability and makes the code cleaner and easier to test.
 * Note: The syntax @__(@Autowired) is used to avoid issues with Java's annotation processing.
 * It allows you to apply multiple annotations to the generated constructor.
 * This is particularly useful when you want to combine Lombok's constructor generation
 * with Spring's dependency injection mechanism.
 * In summary, using @RequiredArgsConstructor(onConstructor = @__(@Autowired))
 * is a convenient way to create service classes in Spring that are properly wired
 * with their dependencies while keeping the code concise and maintainable.
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ProductService {

   // @Autowired
    private final ProductRepository productRepository;

public ProductResponse createProduct(ProductRequest productRequest) {
        var product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product {} created successfully", product);
    return new ProductResponse(product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice());
}

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream().
                map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                )).toList();
    }
}
