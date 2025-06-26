package tech.saintbassanaga.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", nullable = false)
    private UUID id;

    private String name;
    private String description;
    private Double price;

}