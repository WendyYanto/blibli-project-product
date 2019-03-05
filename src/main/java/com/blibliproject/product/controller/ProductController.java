package com.blibliproject.product.controller;

import com.blibliproject.product.model.Product;
import com.blibliproject.product.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(
        value = "/products",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Product> getAllProducts(){
        return productService.getAll();
    }

    @RequestMapping(
        value = "/products",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }

    @RequestMapping(
        value = "/products/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Product findById(@PathVariable("id") long id){
        return productService.findById(id);
    }

    @RequestMapping(
        value = "/products/edit/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product){
        return productService.update(product,id);
    }

    @RequestMapping(
        value = "/products/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Product deleteProduct(@PathVariable("id") long id){
        return productService.delete(id);
    }
}
