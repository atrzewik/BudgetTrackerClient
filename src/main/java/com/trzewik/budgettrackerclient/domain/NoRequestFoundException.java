package com.trzewik.budgettrackerclient.domain;

public class NoRequestFoundException extends RuntimeException {
    public NoRequestFoundException(String message) {
        super(message);
    }
}
