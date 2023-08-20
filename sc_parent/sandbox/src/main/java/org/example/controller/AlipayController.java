package org.example.controller;

import cn.hutool.core.util.IdUtil;
import com.alipay.api.response.AlipayTradePagePayResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.PayRequest;
import org.example.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * @version 1.0.0
 * @date 2023/6/6
 */
@RestController
@RequestMapping("/api/alipay")
@Api(tags = "网站支付宝支付")
@Slf4j
public class AlipayController {
    @Autowired
    private AliPayService alipayService;

    /**
     * 创建支付订单，返回支付页面
     */
    @GetMapping("/pay")
    public ModelAndView pay(){

        String outTradeNo= IdUtil.simpleUUID();
        String subject="测试商品";
        BigDecimal totalAmount = BigDecimal.valueOf(250);

        PayRequest payRequest = new PayRequest();
        payRequest.setOut_trade_no(outTradeNo);
        payRequest.setSubject(subject);
        payRequest.setTotal_amount(totalAmount);
        payRequest.setBody("这是一个测试商品描述哦");
        payRequest.setQr_pay_mode("2");

        AlipayTradePagePayResponse response = alipayService.pay(payRequest);
        if (response == null) {
            return null;
        }
        log.info("响应:{}",response.getBody());
        // 返回支付页面
        ModelAndView modelAndView = new ModelAndView("pay");
        modelAndView.addObject("form", response.getBody());

        return modelAndView;
    }


    /**
     * 处理支付宝异步通知
     */
    @PostMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean signVerified = alipayService.rsaCheckV1(request);
        // 将请求参数转换为Map
        if (signVerified) {
            // 处理支付成功逻辑，更新订单状态等操作
            response.getWriter().write("success"); // 告诉支付宝已经收到通知并处理成功
        } else {
            response.getWriter().write("failure"); // 告诉支付宝验签失败
        }
    }
}
