package tech.saintbassanaga.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.saintbassanaga.app.entities.Product;
import tech.saintbassanaga.app.services.ProductService;

import java.util.List;
import java.util.UUID;

/**
 * Created by saintbassanaga {saintbassanaga}
 * In the Project AuthServer at Thu - 6/26/25
 */


@RestController
@RequestMapping("/api/products")
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Product", description = "Operations related to products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_write')")
    @Operation(summary = "Create a new product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product saved = productService.createProduct(product);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Product found = productService.getProductById(id);
        return ResponseEntity.ok(found);
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product by ID")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_write')")
    @Operation(summary = "Delete a product by ID")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

