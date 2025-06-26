package tech.saintbassanaga.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.saintbassanaga.app.entities.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}