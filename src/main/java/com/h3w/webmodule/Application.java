package com.h3w.webmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
        "com.h3w.config",
        "com.h3w.webmodule.config",
        "com.h3w.webmodule.mapper",
        "com.h3w.webmodule.service",
        "com.h3w.webmodule.controller"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
