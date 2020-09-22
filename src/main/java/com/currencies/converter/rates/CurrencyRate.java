package com.currencies.converter.rates;

import java.time.LocalDate;
import java.util.List;

public class CurrencyRate {

    private String currencyUnit;

    private LocalDate date;

    private List<ConversionRate> conversionRate;

    public CurrencyRate() {
    }

    public CurrencyRate(String currencyUnit, LocalDate date, List<ConversionRate> conversionRate) {
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

    public List<ConversionRate> getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(List<ConversionRate> conversionRate) {
        this.conversionRate = conversionRate;
    }
}
