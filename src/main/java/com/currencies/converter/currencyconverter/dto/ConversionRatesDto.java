package com.currencies.converter.currencyconverter.dto;

import java.util.List;

public class ConversionRatesDto {
    private List<String> currencyUnits;

    public ConversionRatesDto() {
    }

    public ConversionRatesDto(List<String> currencyUnits) {
        this.currencyUnits = currencyUnits;
    }

    public List<String> getCurrencyUnits() {
        return currencyUnits;
    }

    public void setCurrencyUnits(List<String> currencyUnits) {
        this.currencyUnits = currencyUnits;
    }
}
