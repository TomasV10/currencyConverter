package com.currencies.converter.currencyconverter.dto;

import java.math.BigDecimal;

public class ConversionRateDto {
    private Long id;
    private String currencyUnit;

    private BigDecimal rate;

    public ConversionRateDto() {
    }

    public ConversionRateDto(String currencyUnit, BigDecimal rate) {
        this.currencyUnit = currencyUnit;
        this.rate = rate;
    }

    public ConversionRateDto(Long id, String currencyUnit, BigDecimal rate) {
        this.id = id;
        this.currencyUnit = currencyUnit;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
