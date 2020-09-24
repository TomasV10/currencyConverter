package com.currencies.converter.currencyconverter;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.List;

import com.currencies.converter.currencyconverter.dto.ConversionRateDto;
import com.currencies.converter.currencyconverter.dto.CurrencyRateDto;
import com.currencies.converter.currencyconverter.dto.CurrencyRatesDto;
import com.currencies.converter.rates.ConversionRate;
import com.currencies.converter.rates.CurrencyRate;
import com.currencies.converter.rates.CurrencyRatesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public CurrencyRateDto getCurrencyRateById(Long id){
        CurrencyRate currentRate = currencyRatesService.getRateById(id);
        return new CurrencyRateDto(currentRate.getId(), currentRate.getCurrencyUnit(),
                currentRate.getDate(), toConversionRateDto(currentRate.getConversionRate()));
    }

    public BigDecimal getAmountOfConvertedMoney(String fromCurrencyUnit, String toCurrencyUnit,
                                                BigDecimal amountOfMoneyToConvert){
        return currencyRatesService.convertFromEurOrTo(fromCurrencyUnit, toCurrencyUnit, amountOfMoneyToConvert);
    }

    public List<ConversionRateDto>getAllConversionRates(){
        return toConversionRateDto(currencyRatesService.getConversionRates());
    }

    @Transactional(readOnly = true)
    public BigDecimal getRateByCurrencyUnit(String currencyUnit){
        return currencyRatesService.getRateByUnit(currencyUnit);
    }

    private List<CurrencyRateDto> toDto(List<CurrencyRate> currentRatesFor) {
        return currentRatesFor.stream()
                .map(rate -> new CurrencyRateDto(rate.getId(),rate.getCurrencyUnit(), rate.getDate(),
                        toConversionRateDto(rate.getConversionRate())))
                .collect(toList());
    }

    private List<ConversionRateDto> toConversionRateDto(List<ConversionRate> rates) {
        return rates.stream()
                .map(rate -> new ConversionRateDto(rate.getId(),rate.getCurrencyUnit(), rate.getRate()))
                .collect(toList());
    }
}
