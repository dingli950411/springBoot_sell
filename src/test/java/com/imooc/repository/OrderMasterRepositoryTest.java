package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "111111";

    @Test
    public void saveTest(){
        OrderMaster orderMaster= new OrderMaster();
        orderMaster.setOrderId("1234568");
        orderMaster.setBuyerName("丁先生");
        orderMaster.setBuyerPhone("17626037403");
        orderMaster.setBuyerAddress("慕课网");
        orderMaster.setBuyerOpenid("111111");
        orderMaster.setOrderAmount(new BigDecimal(34));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByBuyerOpenid(){
        PageRequest request = new PageRequest(1,10);

        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);
        Assert.assertNotEquals(0,result.getTotalElements());
//        System.out.println(result.getTotalElements());
    }

}