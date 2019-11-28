package com.trzewik.budgettrackerclient;

import com.trzewik.budgettrackerclient.domain.port.spi.RequestPort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Agnieszka Trzewik
 */
@SpringBootApplication
public class BudgetTrackerClientApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(BudgetTrackerClientApplication.class, args);

        context.getBean(RequestPort.class).sendRequest();

        System.exit(0);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
