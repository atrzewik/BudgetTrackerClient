package com.trzewik.budgettrackerclient.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * @author Agnieszka Trzewik
 */
@NoArgsConstructor
@Getter
public class SpendingSummary {

    private BigDecimal summary;
    private OffsetDateTime timestamp;

}
