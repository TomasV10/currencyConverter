package com.currencies.converter.currencyconverter.dto;

import java.math.BigDecimal;

public class ConversionCurrency {
    private String currencyToConvertFrom;
    private String currencyToConvertTo;
    private BigDecimal amount;

    public ConversionCurrency() {
    }

    public ConversionCurrency(String currencyToConvertFrom, String currencyToConvertTo, BigDecimal amount) {
        this.currencyToConvertFrom = currencyToConvertFrom;
        this.currencyToConvertTo = currencyToConvertTo;
        this.amount = amount;
    }

    public String getCurrencyToConvertFrom() {
        return currencyToConvertFrom;
    }

    public void setCurrencyToConvertFrom(String currencyToConvertFrom) {
        this.currencyToConvertFrom = currencyToConvertFrom;
    }

    public String getCurrencyToConvertTo() {
        return currencyToConvertTo;
    }

    public void setCurrencyToConvertTo(String currencyToConvertTo) {
        this.currencyToConvertTo = currencyToConvertTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
