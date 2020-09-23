package com.currencies.converter.currencyconverter.dto;

import java.time.LocalDate;
import java.util.List;

public class CurrencyRateDto {
    private Long id;
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

    public CurrencyRateDto(Long id, String currencyUnit, LocalDate date, List<ConversionRateDto> conversionRate) {
        this.id = id;
        this.currencyUnit = currencyUnit;
        this.date = date;
        this.conversionRate = conversionRate;
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
