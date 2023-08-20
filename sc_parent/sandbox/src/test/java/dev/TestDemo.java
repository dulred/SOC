package dev;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestDemo {

    @Test
    public void test() {
        // 假设用户提供的输入
        String userInput = "<script>alert('XSS attack');</script>";

        // 对用户输入进行HTML编码
        String encodedInput = StringEscapeUtils.escapeHtml4(userInput);

        // 输出编码后的内容
        System.out.println(encodedInput);

    }

    /**
     * BigDecimal和double对比
     * 浮点数 (double 和 float) 与 BigDecimal 之间的差距主要是由于它们的底层表示和运算方式不同。
     * @return
     * @author dulred
     * @date 2023/08/19
     */
    @Test
    public void test1() {
        BigDecimal num1 = new BigDecimal("0.1");
        BigDecimal num2 = new BigDecimal("0.2");

        BigDecimal sum = num1.add(num2);
        System.out.println("Sum using BigDecimal: " + sum);

        double num3 = 0.1;
        double num4 = 0.2;

        double sumDouble = num3 + num4;
        System.out.println("Sum using double: " + sumDouble);
    }

    @Test
    public void test2() {
        BigDecimal principal = new BigDecimal("1000");
        BigDecimal rate = new BigDecimal("0.05");
        int years = 10;

        BigDecimal futureValue = principal.multiply(rate.add(BigDecimal.ONE).pow(years));
        System.out.println("Future value using BigDecimal: " + futureValue);

        double principalDouble = 1000;
        double rateDouble = 0.05;

        double futureValueDouble = principalDouble * Math.pow((1 + rateDouble), years);
        System.out.println("Future value using double: " + futureValueDouble);
    }

    @Test
    public void test3() {
        double doubleValue = 1.0 / 3.0;
        float floatValue = 1.0f / 3.0f;

        System.out.println("Double value: " + doubleValue);
        System.out.println("Float value: " + floatValue);

        BigDecimal bdValue = new BigDecimal("1.0").divide(new BigDecimal("3.0"), 12, BigDecimal.ROUND_HALF_UP);
        System.out.println("BigDecimal value: " + bdValue);
    }
}
