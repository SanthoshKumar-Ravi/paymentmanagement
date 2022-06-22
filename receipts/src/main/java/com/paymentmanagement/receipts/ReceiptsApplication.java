package com.paymentmanagement.receipts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJms
public class ReceiptsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceiptsApplication.class, args);
    }

}
