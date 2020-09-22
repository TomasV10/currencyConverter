package com.currencies.converter.currencyconverter.dto;

import java.util.List;

public class CurrencyRatesDto {

    private List<CurrencyRateDto> rates;

    public CurrencyRatesDto() {
    }

    public CurrencyRatesDto(List<CurrencyRateDto> rates) {
        this.rates = rates;
    }

    public List<CurrencyRateDto> getRates() {
        return rates;
    }

    public void setRates(List<CurrencyRateDto> rates) {
        this.rates = rates;
    }
}
