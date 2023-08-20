package org.example.service.impl;

import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.config.PayAliConfig;
import org.example.entity.PayRequest;
import org.example.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @date 2023/6/7
 */
@Service
@Slf4j
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private PayAliConfig payAliConfig;

    @Override
    public AlipayTradePagePayResponse pay(PayRequest payRequest) {
        // 构造请求参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(payAliConfig.getReturnUrl());
        request.setNotifyUrl(payAliConfig.getNotifyUrl());
        log.info("生成的字符串:{}", JSONUtil.toJsonStr(payRequest));

        request.setBizContent(JSONUtil.toJsonStr(payRequest));
//        request.setBizContent("{\"out_trade_no\":\"" + UUID.randomUUID().toString().replaceAll("-", "") + "\","
//                + "\"total_amount\":\"0.01\","
//                + "\"subject\":\"测试商品\","
//                + "\"body\":\"测试商品详情\","
//                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        // 调用SDK生成表单
        try {
            return alipayClient.pageExecute(request);
        } catch (AlipayApiException e) {
            log.error("支付宝请求失败");
        }
        return null;
    }

    @Override
    public boolean rsaCheckV1(HttpServletRequest request) {
        boolean flag = false;
        Map<String, String> params = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            params.put(paramName, paramValue);
        }
        // 调用SDK验证签名
        try {
            flag = AlipaySignature.rsaCheckV1(params, payAliConfig.publicKey, AlipayConstants.CHARSET_UTF8, AlipayConstants.SIGN_TYPE_RSA2);
        } catch (AlipayApiException e) {
            log.error("验签失败");
        }
        return flag;
    }
}


