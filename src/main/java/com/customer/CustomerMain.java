package com.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CustomerMain {


    private static Logger LOGGER = LoggerFactory.getLogger(CustomerMain.class);

    public static void main(String[] args) {

        SpringApplication.run(CustomerMain.class, args);
        LOGGER.info("Hello i am in Customer Main class");
        LOGGER.debug("Application is in debugging mode");
    }
}
