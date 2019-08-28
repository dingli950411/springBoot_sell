package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "1234567";

    private final String ORDER_ID = "1554815287330796501";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("丁先生");
        orderDTO.setBuyerAddress("香溢紫郡");
        orderDTO.setBuyerPhone("17626037403");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123457");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123458");
        orderDetail1.setProductQuantity(2);
        orderDetailList.add(orderDetail1);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("创建订单，result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}",orderDTO);
        Assert.assertEquals(orderDTO.getOrderId(),ORDER_ID);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,10);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID,pageRequest);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(result.getOrderStatus(), OrderStatusEnum.CANCEL.getCode());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(result.getOrderStatus(), OrderStatusEnum.FINISH.getCode());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(result.getPayStatus(), PayStatusEnum.SUCCESS.getCode());
    }

    @Test
    public void list(){
        PageRequest pageRequest = new PageRequest(0,10);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
//        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
        Assert.assertTrue("卖家端查询所有订单列表",orderDTOPage.getTotalElements()>0);
    }
}