package com.blibliproject.product;

import com.blibliproject.product.model.Product;
import com.blibliproject.product.repository.ProductRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void productListMustReturnNull(){
        List<Product> lists = productRepository.findAll();
        Assert.assertTrue("List Must Return Size of 0 On First Create",lists.size() == 0);
    }

    @Test
    public void productCreatedMustReturnTheSameObjectInserted(){
        Product firstProduct = new Product(
            null,
            "Nasi",
            200000,
            "main.jpg",
            "thumb.jpg",
            20,
            100,
            1
        );

        Product firstProductInserted = productRepository.save(firstProduct);

        Assert.assertTrue("firstProduct Is The Same As firstProductInserted",firstProduct == firstProductInserted);
    }

    @Test
    public void productCreatedCanBeFindById(){
        Product firstProduct = new Product(
            null,
            "Nasi",
            200000,
            "main.jpg",
            "thumb.jpg",
            20,
            100,
            1
        );

        Product firstProductInserted = productRepository.save(firstProduct);
        Long firstProductInsertedId = firstProductInserted.getId();

        Optional<Product> findProductByID = productRepository.findById(firstProductInsertedId);

        Assert.assertTrue("firstProductInserted Must Return Same ID as findMemberByID",findProductByID.get().getId() == firstProductInsertedId);
    }

    @Test
    public void productNotCreatedCannotBeFindByID(){

        Product firstProduct = new Product(
            null,
            "Nasi",
            200000,
            "main.jpg",
            "thumb.jpg",
            20,
            100,
            1
        );

        productRepository.save(firstProduct);

        Optional<Product> findProductByID = productRepository.findById(100L);

        Assert.assertTrue("findProductByID Must Return NULL",findProductByID.isPresent() == false);
    }

    @Test
    public void productGetAllMustReturnTheSameSizeInserted(){
        Product firstProduct = new Product(
            null,
            "Nasi",
            200000,
            "main.jpg",
            "thumb.jpg",
            20,
            100,
            1
        );

        Product secondProduct = new Product(
            null,
            "Gorengan",
            10000,
            "main.jpg",
            "thumb.jpg",
            20,
            100,
            1
        );

        Product firstProductReturn = productRepository.save(firstProduct);
        Product secondProductReturn = productRepository.save(secondProduct);

        List<Product> lists = productRepository.findAll();

        Assert.assertTrue("firstMemberInserted Has Same Name As firstMember",firstProduct.getName().equals(firstProductReturn.getName()));
        Assert.assertTrue("secondMemberInserted Has Same Name As secondMember",secondProduct.getName().equals(secondProductReturn.getName()));
        Assert.assertTrue("lists Must Return Size of 2",lists.size() == 2);
    }

    @Test
    public void productUpdateMustReturnUpdatedObjectAndSameID(){
        Product firstProduct = new Product(
            null,
            "Nasi",
            200000,
            "main.jpg",
            "thumb.jpg",
            20,
            100,
            1
        );

        Product firstProductInserted = productRepository.save(firstProduct);
        Long ID = firstProductInserted.getId();

        Product secondProduct = new Product(
                ID,
            "Gorengan",
            10000,
            "main.jpg",
            "thumb.jpg",
            20,
            100,
            1
        );

        Product expectedReturnID = productRepository.save(secondProduct);

        Assert.assertTrue("ID Should Be The Same",ID == expectedReturnID.getId());
        Assert.assertTrue("Name Cannot The Same",expectedReturnID.getName().equals("Nasi") == false);
        Assert.assertTrue("Name Must Have Change",expectedReturnID.getName().equals("Gorengan") == true);

    }

    @Test
    public void deleteMember(){
        Product firstProduct = new Product(
            null,
            "Nasi",
            200000,
            "main.jpg",
            "thumb.jpg",
            20,
            100,
            1
        );

        Product GetID = productRepository.save(firstProduct);
        productRepository.deleteById(GetID.getId());

        Boolean checkID = productRepository.existsById(GetID.getId());
        List<Product> lists = productRepository.findAll();

        Assert.assertTrue("checkID Must Return False",checkID == false);
        Assert.assertTrue("List Must Return 0",lists.size() == 0);
    }

    @After
    public void deleteAllDataInTable(){
        productRepository.deleteAll();
    }

}
