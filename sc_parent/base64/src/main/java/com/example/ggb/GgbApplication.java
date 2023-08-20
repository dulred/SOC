package com.example.ggb;

import com.example.ggb.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class GgbApplication {

    @Autowired
    private  TestService testService;
    public static void main(String[] args) {
        SpringApplication.run(GgbApplication.class, args);
    }
    @PostConstruct
    public void test(){
        testService.test();
    }


}
