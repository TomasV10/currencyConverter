package com.currencies.converter.currencyconverter.dto;

import java.time.LocalDate;
import java.util.List;

public class CurrencyRateDto {

    private String currencyUnit;
    private LocalDate date;
    private List<ConversionRateDto> conversionRate;

    public CurrencyRateDto() {
    }

    public CurrencyRateDto(String currencyUnit, LocalDate date, List<ConversionRateDto> conversionRate) {
        this.currencyUnit = currencyUnit;
        this.date = date;
        this.conversionRate = conversionRate;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ConversionRateDto> getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(List<ConversionRateDto> conversionRate) {
        this.conversionRate = conversionRate;
    }
}
