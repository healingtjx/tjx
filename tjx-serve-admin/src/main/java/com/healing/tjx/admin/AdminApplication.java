package com.healing.tjx.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * admin-启动类
 *
 * @author tjx
 **/
@SpringBootApplication(scanBasePackages = "com.healing.tjx")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}

