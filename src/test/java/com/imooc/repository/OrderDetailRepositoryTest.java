package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("6543218");
        orderDetail.setOrderId("1234567");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("123123124");
        orderDetail.setProductName("收纳盒");
        orderDetail.setProductPrice(new BigDecimal(10));
        orderDetail.setProductQuantity(2);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByOrderId(){
        List<OrderDetail> orderDetailList = repository.findByOrderId("1234567");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}