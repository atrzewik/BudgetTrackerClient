package com.trzewik.budgettrackerclient.domain;

import java.util.Arrays;

/**
 * @author Agnieszka Trzewik
 */
public enum Request {
    GET_SPENDINGS("getList"),
    POST_SPENDING("send"),
    GET_SPENDINGS_SUMMARY("getSummary");

    private final String value;

    Request(String value) {
        this.value = value;
    }

    public static Request matchRequest(String givenValue) {
        return Arrays.stream(values())
                .filter(request -> request.value.equals(givenValue))
                .findFirst()
                .orElseThrow(() -> {
                    throw new NoRequestFoundException("Wrong request given!");
                });
    }

}
