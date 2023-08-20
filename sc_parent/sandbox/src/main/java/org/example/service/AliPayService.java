package org.example.service;

import com.alipay.api.response.AlipayTradePagePayResponse;
import org.example.entity.PayRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0.0
 * @date 2023/6/7
 */
public interface AliPayService {

    AlipayTradePagePayResponse pay(PayRequest payRequest);

    boolean rsaCheckV1(HttpServletRequest request);
}

