package com.trzewik.budgettrackerclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author Agnieszka Trzewik
 */
@SpringBootApplication
public class BudgetTrackerClientApplication {


    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(BudgetTrackerClientApplication.class, args);

    }


}
