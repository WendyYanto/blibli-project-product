package com.blibliproject.product;

import com.blibliproject.product.model.Product;
import com.blibliproject.product.service.ProductServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class ProductServiceTest {

    private ProductServiceImplementation service;

    @Before
    public void createProductServiceInstance(){
        service = new ProductServiceImplementation();
    }

    @Test
    public void testProductsIsNullBeforeInsert(){
        List<Product> data = service.getAll();
        Assert.assertTrue("NullPointerException Invoke On GetAll List Without Inserting",data == null);
    }

    @Test
    public void testProductCreated(){
        Product current = new Product(
                1,
                "Name",
                100,
                "mainImage.jpg",
                "thumbNailImage.jpg",
                10,
                10,
                1
        );

        Product response = service.create(current);

        Assert.assertTrue("Current Product Created Is The Same With Object Product In List",service.getAll().size() == 1);
    }

    @Test
    public void testProductCreatedAndFind(){
        Product current = new Product(
                1,
                "Name",
                100,
                "mainImage.jpg",
                "thumbNailImage.jpg",
                10,
                10,
                1
        );

        service.create(current);

        Product findProduct = service.findById(1);

        Assert.assertTrue("Product Object Is The Same To Object Added In List",findProduct == current);
        Assert.assertTrue("Product Object Has Id of 1",findProduct.getId() == 1);
        Assert.assertTrue("Product Object Has Name of 'Name' ",findProduct.getName().equals("Name"));
    }

    @Test
    public void testProductGetAllReturnTheSameSize(){

        Product first_current = new Product(
                1,
                "Name",
                100,
                "mainImage.jpg",
                "thumbNailImage.jpg",
                10,
                10,
                1
        );

        Product second_current = new Product(
                2,
                "Name1",
                100,
                "mainImage1.jpg",
                "thumbNailImage1.jpg",
                10,
                10,
                1
        );

        service.create(first_current);
        service.create(second_current);

        List<Product> responseLists = service.getAll();

        Assert.assertTrue("Product GetAll Must Return Size Of 2",responseLists.size() == 2);
    }

    @Test
    public void testProductCannotInsertDuplicateID(){

        Product first_current = new Product(
                1,
                "Name",
                100,
                "mainImage.jpg",
                "thumbNailImage.jpg",
                10,
                10,
                1
        );

        Product second_current = new Product(
                1,
                "Name",
                100,
                "mainImage.jpg",
                "thumbNailImage.jpg",
                10,
                10,
                1
        );

        Product firstProductInserted = service.create(first_current);
        Product secondProductInserted = service.create(second_current);

        List<Product> responseLists = service.getAll();

        Assert.assertTrue("secondProductInserted Must Return Null",secondProductInserted == null);
        Assert.assertTrue("ResponseList Must Only Has The Size Of 1",responseLists.size() == 1);
    }

    @Test
    public void productUpdateData(){
        Product firstCurrent = new Product(
                1,
                "Name",
                100,
                "mainImage.jpg",
                "thumbNailImage.jpg",
                10,
                10,
                1
        );

        Product updateCurrent = new Product(
                1,
                "New_Name",
                100,
                "newMainImage.jpg",
                "newThumbNailImage.jpg",
                10,
                10,
                1
        );

        service.create(firstCurrent);

        Product retrievedProduct = service.findById(1);

        Assert.assertTrue("retrievedProduct Must Not Null",retrievedProduct != null);

        Product updated = service.update(updateCurrent,1);

        Assert.assertTrue("updateCurrent Has The Same Name AS Updated",updated.getName().equals(updateCurrent.getName()));
        Assert.assertTrue("updateCurrent Has The Same ID of 1",updated.getId() == 1);
    }

    @Test
    public void testUpdateProductReturnNullIfIDIsNotPresent(){
        Product firstCurrent = new Product(
                1,
                "Name",
                100,
                "mainImage.jpg",
                "thumbNailImage.jpg",
                10,
                10,
                1
        );

        Product updateCurrent = new Product(
                1,
                "New_Name",
                100,
                "newMainImage.jpg",
                "newThumbNailImage.jpg",
                10,
                10,
                1
        );

        service.create(firstCurrent);
        Product updated = service.update(updateCurrent,2);

        Assert.assertTrue("updated Must Return NULL",updated == null);
    }

    @Test
    public void testDeleteReturnNullIfIDNotPresent(){
        Product firstCurrent = new Product(
                1,
                "Name",
                100,
                "mainImage.jpg",
                "thumbNailImage.jpg",
                10,
                10,
                1
        );

        service.create(firstCurrent);

        Product response = service.delete(2);

        Assert.assertTrue("response Must Return NULL",response == null);
    }

    @Test
    public void testDelete(){

        Product first_current = new Product(
                1,
                "Name",
                100,
                "mainImage.jpg",
                "thumbNailImage.jpg",
                10,
                10,
                1
        );

        Product second_current = new Product(
                2,
                "2Name",
                100,
                "2mainImage.jpg",
                "2thumbNailImage.jpg",
                10,
                10,
                1
        );

        service.create(first_current);
        service.create(second_current);

        Product productDeleteId2 = service.delete(2);

        List<Product> responseLists = service.getAll();

        Assert.assertTrue("productDeleteId2 Must Contain ID of 2",productDeleteId2.getId() == 2);
        Assert.assertTrue("Total responseList Must Only Have Size of 1",responseLists.size() == 1);
    }
}
