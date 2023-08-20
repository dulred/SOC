package org.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 支付配置类
 */

@Configuration
@ConfigurationProperties(prefix = "alipay")
@Data
public class PayAliConfig {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    public  String appId;

    /**
     * 应用私钥，您的PKCS8格式RSA2私钥
     */
    public String privateKey;

    /**
     * 支付宝公钥
     */
    public String publicKey;

    /**
     * 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public  String notifyUrl;

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public  String returnUrl;

    /**
     * 支付宝网关
     */
    public String gatewayUrl;

}
