package com.currencies.converter.currencyconverter.dto;

import java.math.BigDecimal;

public class ConversionCurrency {
    private String convertFrom;
    private String convertTo;
    private BigDecimal amountOfMoney;

    public ConversionCurrency() {
    }

    public ConversionCurrency(String convertFrom, String convertTo, BigDecimal amountOfMoney) {
        this.convertFrom = convertFrom;
        this.convertTo = convertTo;
        this.amountOfMoney = amountOfMoney;
    }

    public String getConvertFrom() {
        return convertFrom;
    }

    public void setConvertFrom(String convertFrom) {
        this.convertFrom = convertFrom;
    }

    public String getConvertTo() {
        return convertTo;
    }

    public void setConvertTo(String convertTo) {
        this.convertTo = convertTo;
    }

    public BigDecimal getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(BigDecimal amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }
}
