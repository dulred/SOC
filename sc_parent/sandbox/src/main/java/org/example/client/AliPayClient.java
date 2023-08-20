package org.example.client;

import com.alipay.api.*;
import org.example.config.PayAliConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 初始化AlipayClient
 * @version 1.0.0
 * @date 2023/6/7
 */
@Component
public class AliPayClient {

    @Autowired
    private PayAliConfig payAliConfig;

    @Bean
    public AlipayClient AlipayClient()  throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        //设置网关地址
        alipayConfig.setServerUrl(payAliConfig.gatewayUrl);
        //设置应用ID
        alipayConfig.setAppId(payAliConfig.appId);
        //设置应用私钥
        alipayConfig.setPrivateKey(payAliConfig.privateKey);
        //设置请求格式，固定值json
        alipayConfig.setFormat(AlipayConstants.FORMAT_JSON);
        //设置字符集
        alipayConfig.setCharset(AlipayConstants.CHARSET_UTF8);
        //设置支付宝公钥
        alipayConfig.setAlipayPublicKey(payAliConfig.publicKey);
        //设置签名类型
        alipayConfig.setSignType(AlipayConstants.SIGN_TYPE_RSA2);
        //构造client
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        return alipayClient;
    }
}
