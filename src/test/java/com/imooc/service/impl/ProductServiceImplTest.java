package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0,productInfoList);
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0,10);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("椒盐大虾");
        productInfo.setProductPrice(new BigDecimal(88));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的大虾");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(4);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void onSale(){
        ProductInfo productInfo = productService.onSale("123457");
        Assert.assertEquals(ProductStatusEnum.UP.getCode(),productInfo.getProductStatus());

    }

    @Test
    public void offSale(){
        ProductInfo productInfo = productService.offSale("123457");
        Assert.assertEquals(ProductStatusEnum.DOWN.getCode(),productInfo.getProductStatus());
    }
}