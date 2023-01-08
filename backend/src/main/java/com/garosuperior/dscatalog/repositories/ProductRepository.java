package com.garosuperior.dscatalog.repositories;

import com.garosuperior.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
