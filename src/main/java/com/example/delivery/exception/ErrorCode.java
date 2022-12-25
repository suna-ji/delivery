package com.example.delivery.exception;

public enum ErrorCode {
    // Common
    REQUIRED_FIELD_EMPTY(1000),
    FAIL_TO_GET_CURRENT_USER_ID(1001),

    // User
    PASSWORD_PATTERN_NOT_MATCH(2000),
    LOGIN_FAIL(2001),

    // Delivery
    FROM_DATE_IS_NOT_BEFORE_THE_TO_DATE(3000),
    DELIVERY_SEARCH_PERIOD_HAS_EXCEEDED_3_DAYS(3001),
    FAIL_TO_MODIFY_DELIVERY(3002),

    // DORO
    INVALID_ADDRESS(4000);

    private final Integer errorCode;

    private ErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
