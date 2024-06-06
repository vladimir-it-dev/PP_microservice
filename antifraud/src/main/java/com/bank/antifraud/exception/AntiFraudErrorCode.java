package com.bank.antifraud.exception;

public enum AntiFraudErrorCode {
    DAY_LIMIT("Превышение дневного лимита"),
    MONTH_LIMIT("Превышение месячного лимита"),
    NOT_AUTHENTICATED("Пользователь не аутентифицирован"),

    TOO_MANY_INCOMING_TRANSFERS("Слишком много входящих транзакций по указанным реквизитам"),
    FIRST_TRANSFER("Покупка совершается впервые"),
    PURCHASE_LIMIT("Превышен лимит разовой покупки"),
    HAS_BLOCKED_TRANSFERS("У пользователя имеются заблокированные транзакции за день"),
    AVERAGE_LIMIT("Сумма покупки существенно выше обычного");

    private final String errorCode;

    AntiFraudErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorCode() {
        return errorCode;
    }

    }
