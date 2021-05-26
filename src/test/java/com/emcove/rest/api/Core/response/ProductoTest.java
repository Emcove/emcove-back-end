package com.emcove.rest.api.Core.response;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductoTest {

    @Test
    public void toJson(){
        Producto producto = new Producto(5, "Torta", "Torta de chocolate", 1500.05F);
        System.out.println(producto.toJson());

    }

    @Test
    public void toJsonList(){
        List<Producto> productList = new ArrayList<>();
        productList.add(new Producto(1, "Torta", "Torta de chocolate", 1500.05F));
        productList.add(new Producto(2, "Torta", "Torta de manzana", 140.05F));
        productList.add(new Producto(3, "Torta", "Torta de Chips", 2400.05F));
        productList.add(new Producto(4, "Torta", "Torta de vainilla", 500.05F));
        productList.add(new Producto(5, "Torta", "Selva negra", 2500.05F));

        Gson gson = new Gson();
        System.out.println(gson.toJson(productList));
        System.out.println(gson.toJson(null));

    }


}
