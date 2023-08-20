package com.example.ggb.utils;

import java.util.Base64;

public class TestBase64 {

    public static void main(String[] args) {
//            模拟图片数据
        byte[] bytes = {0x16,0x21,0x22,0x23,0x13};

//        16进制转为base64
        String base64 = Base64.getEncoder().encodeToString(bytes);
        System.out.println(base64);

//        base64 转 16进制
        byte[] decode = Base64.getDecoder().decode(base64);
        for (byte b : decode) {
            System.out.printf("%02X ", b);
        }

    }


}
