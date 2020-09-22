package com.currencies.converter.currencyconverter.dto;

import java.math.BigDecimal;

public class ConversionRateDto {

    private String currencyUnit;

    private BigDecimal rate;

    public ConversionRateDto() {
    }

    public ConversionRateDto(String currencyUnit, BigDecimal rate) {
        this.currencyUnit = currencyUnit;
        this.rate = rate;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
