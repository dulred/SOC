package org.example.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付请求
 *
 * @author dulred
 * @date 2023/08/19
 */
@Data
public class PayRequest {
    /**
     * 订单编号
     */
    private String out_trade_no;

    /**
     * 商品名称
     */
    private String subject;

    /**
     * 支付金额
     */
    private BigDecimal total_amount;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
     */
    private String product_code="FAST_INSTANT_TRADE_PAY";

    /**
     * PC扫码支付的方式
     */
    private String qr_pay_mode;

    /**
     * 支付渠道
     */
    private String enable_pay_channels/*="balance,pcredit,moneyFund,bankPay,creditCard,pcreditpayInstallment"*/;

    /**
     * 商户自定义二维码宽度。
     * 注：qr_pay_mode=4时该参数有效
     */
    private String qrcode_width;


}
