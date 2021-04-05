package com.java.onlinevideo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.java.onlinevideo.mapper")
@EnableTransactionManagement
public class OnlinevideoApplication {

    public static void main(String[] args) {
        System.out.print("git测试修改");
        SpringApplication.run(OnlinevideoApplication.class, args);
    }

}
