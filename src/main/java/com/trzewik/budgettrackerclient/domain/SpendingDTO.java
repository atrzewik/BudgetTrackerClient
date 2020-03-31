package com.trzewik.budgettrackerclient.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Agnieszka Trzewik
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SpendingDTO {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private String description;
    private BigDecimal price;

    @Override
    public String toString() {
        return "\n Description: " + ANSI_RED + description + ANSI_RESET + " - Price: " + ANSI_GREEN + price + ANSI_RESET;
    }
}