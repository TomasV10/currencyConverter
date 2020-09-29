package com.currencies.converter.currencyconverter;

import com.currencies.converter.currencyconverter.dto.ConversionCurrency;
import com.currencies.converter.currencyconverter.dto.ConversionRatesDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/api/currencies", produces = APPLICATION_JSON_VALUE)
public class CurrencyConverterController {

    private final CurrencyConverterService currencyConverterService;

    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @GetMapping
    public ConversionRatesDto getListOfCurrencies(@RequestParam("baseUnit") String baseUnit) {
        return currencyConverterService.getConversionRatesFor(baseUnit);
    }

    @GetMapping(path = "/test")
    public ConversionRatesDto getListOfCurrencies() {
        return currencyConverterService.getConversionRatesFor("EU");
    }


    @PostMapping("/convert")
    public BigDecimal getConvertedAmount(@RequestBody ConversionCurrency conversionCurrency){
        return currencyConverterService.getAmountOfConvertedMoney(conversionCurrency);
    }


}
