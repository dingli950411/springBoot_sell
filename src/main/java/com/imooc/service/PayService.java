package com.imooc.service;

import com.imooc.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface PayService {

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
