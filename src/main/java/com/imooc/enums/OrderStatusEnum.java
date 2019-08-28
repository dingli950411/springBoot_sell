package com.imooc.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0,"新订单"),
    FINISH(1,"完结"),
    CANCEL(2,"已取消");

    private Integer code;
    private String desc;

    OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
