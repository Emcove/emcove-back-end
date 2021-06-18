package com.emcove.rest.api.Core.response;

import com.emcove.rest.api.Core.utilities.ResponseUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class ProductTest {

    @Test
    public void noBadWordTest(){
        Product product = new Product();
        product.setName("falopa");
        product.setDescription("hola");

        Assert.isTrue(!ResponseUtils.validWord(product.getName()), "It has to be a not valid word");
        Assert.isTrue(ResponseUtils.validWord(product.getDescription()), "It has to be a valid word");
    }


}
