package com.teacherfinder.offers.domain.model.Enum;

public enum Currency {
    PEN("pen"),
    USD("usd"),
    EUR("eur"),
    GBP("gbp"),
    JPY("jpy"),
    CNY("cny");

    private String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}