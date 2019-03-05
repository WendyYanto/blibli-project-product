package com.blibliproject.product.service;

import com.blibliproject.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService{

    private List<Product> data = new ArrayList<Product>();

    @Override
    public Product create(Product product) {

        if(data.size() > 0 && findById((long) product.getId()) != null){
            return null;
        }

        data.add(product);
        return product;
    }

    @Override
    public Product findById(long id) {

        for(int i=0;i<data.size();i++){
            if(data.get(i).getId() == (int) id){
                return data.get(i);
            }
        }

        return null;
    }

    @Override
    public List<Product> getAll() {
        if(data.size() == 0){
            return null;
        }
        return data;
    }

    @Override
    public Product update(Product product, long id) {
        Product current = findById(id);

        if(current != null){
            current.setName(product.getName());
            current.setMain_image(product.getMain_image());
            current.setPrice(product.getPrice());
            current.setQuantity(product.getQuantity());
            current.setRating(product.getRating());
            current.setThumbnail_image(product.getThumbnail_image());
            current.setCategoryId(product.getCategoryId());

            return current;
        }

        return null;
    }

    @Override
    public Product delete(long id) {
        Product current = findById(id);

        if(current != null){
            data.remove(current);
            return current;
        }

        return null;
    }
}
