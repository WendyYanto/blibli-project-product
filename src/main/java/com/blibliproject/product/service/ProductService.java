package com.blibliproject.product.service;

import com.blibliproject.product.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    Product create(Product product);

    Product findById(long id);

    List<Product> getAll();

    Product update(Product product, long id);

    Product delete(long id);
}
