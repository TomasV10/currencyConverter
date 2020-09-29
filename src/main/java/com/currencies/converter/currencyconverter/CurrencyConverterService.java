package com.currencies.converter.currencyconverter;
import com.currencies.converter.currencyconverter.dto.ConversionCurrency;
import com.currencies.converter.currencyconverter.dto.ConversionRateDto;
import com.currencies.converter.currencyconverter.dto.ConversionRatesDto;
import com.currencies.converter.rates.ConversionRate;
import com.currencies.converter.rates.CurrencyRatesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CurrencyConverterService {

    private final CurrencyRatesService currencyRatesService;

    public CurrencyConverterService(CurrencyRatesService currencyRatesService) {
        this.currencyRatesService = currencyRatesService;
    }

    @Transactional(readOnly = true)
    public ConversionRatesDto getConversionRatesFor(String baseCurrencyUnit){
        List<ConversionRate> currentRatesFor = currencyRatesService.getCurrentRatesFor(baseCurrencyUnit);
        if(currentRatesFor.isEmpty()){
            throw new IllegalStateException("Conversion Rates not found");
        } else return new ConversionRatesDto(toDto(currentRatesFor));
    }

    public BigDecimal getAmountOfConvertedMoney(ConversionCurrency conversionCurrency){
        return currencyRatesService.convertFromCurrencyToCurrency(conversionCurrency);
    }

    private List<ConversionRateDto> toDto(List<ConversionRate> conversionRatesFor) {
        return conversionRatesFor.stream()
                .map(rate -> new ConversionRateDto(rate.getId(),rate.getCurrencyFrom(), rate.getCurrencyTo(),
                        rate.getRate(), rate.getDate()))
                .collect(toList());
    }
}
