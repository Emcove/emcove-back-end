package com.emcove.rest.api.Core.response;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@SpringBootTest
public class EntrepreneurshipTest {
    @Test
    public void hasProduct(){
        Entrepreneurship entrepreneurship = new Entrepreneurship();
        Product product = new Product();
        product.setId(1);
        entrepreneurship.addProduct(product);
        Product product2 = new Product();
        product2.setId(2);
        Assert.isTrue(entrepreneurship.hasProduct(product),"Product with id 1 have to be on the entrepreneurship");
        Assert.isTrue(!entrepreneurship.hasProduct(product2), "Product with id 2 doesnt have to be on the entrepreneurship");
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void test(){
        String one = "1";
        String cero = "0";

        System.out.println(one.compareTo(cero));

        List<String> list = new ArrayList<>();

        list.add(cero);
        list.add(cero);
        list.add(cero);
        list.add(one);
        list.add(cero);
        list.add(one);
        list.add(one);
        list.add(cero);
        list.add(cero);

        Collections.sort(list);

        for(String s : list)
            System.out.println(s);
    }
}
