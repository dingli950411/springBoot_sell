package com.imooc.handler;

import com.imooc.VO.ResultVO;
import com.imooc.config.ProjectUrlConfig;
import com.imooc.exception.ResponseBankException;
import com.imooc.exception.SellException;
import com.imooc.exception.SellerAuthorizeException;
import com.imooc.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    private static final String TEMPREDIRECTURL = "https://open.weixin.qq.com/connect/qrconnect" +
            "?appid=wx6ad144e54af67d87" +
            "&redirect_uri=http%3A%2F%2Fsell.springboot.cn%2Fsell%2Fqr%2FoTgZpwcDkm-tlJLd7DT4TmknJ1Wk" +
            "&response_type=code" +
            "&scope=snsapi_login" +
            "&state=http://lili.natapp1.cc/sell/wechat/qrUserInfo";

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView("redirect:".concat(TEMPREDIRECTURL));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handlerResponseBankException(){}
}
