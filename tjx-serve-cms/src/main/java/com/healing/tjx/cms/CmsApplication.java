package com.healing.tjx.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * cms-启动类
 *
 * @author tjx
 **/
@SpringBootApplication(scanBasePackages = "com.healing.tjx")
public class CmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }

}

