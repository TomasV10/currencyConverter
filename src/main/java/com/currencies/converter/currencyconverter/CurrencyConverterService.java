package com.currencies.converter.currencyconverter;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.currencies.converter.currencyconverter.dto.ConversionRateDto;
import com.currencies.converter.currencyconverter.dto.CurrencyRateDto;
import com.currencies.converter.currencyconverter.dto.CurrencyRatesDto;
import com.currencies.converter.rates.ConversionRate;
import com.currencies.converter.rates.CurrencyRate;
import com.currencies.converter.rates.CurrencyRatesService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterService {

    private final CurrencyRatesService currencyRatesService;

    public CurrencyConverterService(CurrencyRatesService currencyRatesService) {
        this.currencyRatesService = currencyRatesService;
    }

    public CurrencyRatesDto getConversionRatesFor(String baseCurrencyUnit) {
        List<CurrencyRate> currentRatesFor = currencyRatesService.getCurrentRatesFor(baseCurrencyUnit);
        return new CurrencyRatesDto(toDto(currentRatesFor));
    }

    private List<CurrencyRateDto> toDto(List<CurrencyRate> currentRatesFor) {
        return currentRatesFor.stream()
                .map(rate -> new CurrencyRateDto(rate.getCurrencyUnit(), rate.getDate(), toConversionRateDto(rate.getConversionRate())))
                .collect(toList());
    }

    private List<ConversionRateDto> toConversionRateDto(List<ConversionRate> rates) {
        return rates.stream()
                .map(rate -> new ConversionRateDto(rate.getCurrencyUnit(), rate.getRate()))
                .collect(toList());
    }
}
