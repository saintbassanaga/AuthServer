package tech.saintbassanaga.app.services;

import org.springframework.stereotype.Service;
import tech.saintbassanaga.app.entities.Product;

import java.util.List;
import java.util.UUID;

/**
 * Created by {saintbassanaga}
 * In the Project AuthServer at Thu - 6/26/25
 */
@Service
public interface ProductService {

    Product createProduct(Product product);
    Product getProductById(UUID id);
    List<Product> getAllProducts();
    Product updateProduct(UUID id, Product product);
    void deleteProduct(UUID id);

}
