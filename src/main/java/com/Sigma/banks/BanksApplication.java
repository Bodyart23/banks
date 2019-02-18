package com.Sigma.banks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class BanksApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(BanksApplication.class, args);
    }

}

