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
        Product product = new Product(5, "Torta", "Torta de chocolate", 1500.05F, new HashMap<String,String[]>());
        System.out.println(product);

    }

    @Test
    public void toJsonList(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Torta", "Torta de chocolate", 1500.05F, new HashMap<>()));
        productList.add(new Product(2, "Torta", "Torta de manzana", 140.05F, new HashMap<>()));
        productList.add(new Product(3, "Torta", "Torta de Chips", 2400.05F, new HashMap<>()));
        productList.add(new Product(4, "Torta", "Torta de vainilla", 500.05F, new HashMap<>()));
        productList.add(new Product(5, "Torta", "Selva negra", 2500.05F, new HashMap<>()));

        Gson gson = new Gson();
        System.out.println(gson.toJson(productList));
        System.out.println(gson.toJson(null));

    }


}
