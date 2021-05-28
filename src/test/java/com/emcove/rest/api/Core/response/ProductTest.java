package com.emcove.rest.api.Core.response;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
public class ProductTest {

    @Test
    public void toJson(){
        Product product = new Product(5, "Torta", "Torta de chocolate", 1500.05F);
        System.out.println(product);

    }

    @Test
    public void toJsonList(){
        List<Product> productList = new ArrayList<>();


        Gson gson = new Gson();
        System.out.println(gson.toJson(productList));
        System.out.println(gson.toJson(null));

    }


}
