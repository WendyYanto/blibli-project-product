package com.blibliproject.product.service;

import com.blibliproject.product.model.Product;
import com.blibliproject.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> data = productRepository.findById(id);

        if(data.isPresent()){
            return data.get();
        }

        return null;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product, Long id) {

        Product result = findById(id);

        if(result != null){
            product.setId(result.getId());
            productRepository.save(product);
        }

        return null;
    }

    @Override
    public Product delete(Long id) {
        Product result = findById(id);

        if(result != null){
            productRepository.deleteById(id);
            return result;
        }

        return null;
    }
}
