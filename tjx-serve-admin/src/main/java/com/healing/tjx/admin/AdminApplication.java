package com.healing.tjx.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @作者: tjx
 * @描述: admin-启动类
 * @创建时间: 创建于15:05 2020-12-09
 **/
@SpringBootApplication(scanBasePackages = "com.healing.tjx")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
