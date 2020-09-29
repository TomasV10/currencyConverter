package com.currencies.converter.currencyconverter.dto;

import java.util.List;

public class ConversionRatesDto {
    List<ConversionRateDto>rates;

    public ConversionRatesDto() {
    }

    public ConversionRatesDto(List<ConversionRateDto> rates) {
        this.rates = rates;
    }

    public List<ConversionRateDto> getRates() {
        return rates;
    }

    public void setRates(List<ConversionRateDto> rates) {
        this.rates = rates;
    }

}
