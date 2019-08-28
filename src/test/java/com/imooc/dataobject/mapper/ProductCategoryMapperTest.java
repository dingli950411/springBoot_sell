package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("categoryName","丽丽最爱");
        map.put("category_type",101);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);

    }

    @Test
    public void insertByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("丽丽最爱2");
        productCategory.setCategoryType(1234);
        int result =mapper.insertByObject(productCategory);

        Assert.assertEquals(1,result);

    }

    @Test
    public void findByCategoryType(){
        ProductCategory productCategory = mapper.findByCategoryType(333);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void findByCategoryName(){
        List<ProductCategory> productCategory = mapper.findByCategoryName("男生专享");
        Assert.assertNotEquals(0,productCategory.size());
    }

    @Test
    public void updateByCategoryType(){
        int result = mapper.updateByCategoryType("情趣",11);
        Assert.assertEquals(1,result);
    }

    @Test
    public void updateByObject(){

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("丽丽最爱");
        productCategory.setCategoryType(11);
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteCategoryType(){
        int result = mapper.deleteByCategoryType(11);
        Assert.assertEquals(1,result);
    }

    @Test
    public void selectByCategoryType(){
        ProductCategory productCategory = mapper.selectByCategoryType(333);
        assertNotNull(productCategory);
    }



}