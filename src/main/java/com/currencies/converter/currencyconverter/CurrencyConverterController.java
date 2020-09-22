package com.currencies.converter.currencyconverter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.currencies.converter.currencyconverter.dto.CurrencyRatesDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/currencies", produces = APPLICATION_JSON_VALUE)
public class CurrencyConverterController {

    private final CurrencyConverterService currencyConverterService;

    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @GetMapping
    public CurrencyRatesDto getListOfCurrencies(@RequestParam("baseUnit") String baseUnit) {
        return currencyConverterService.getConversionRatesFor(baseUnit);
    }

    @GetMapping(path = "/test")
    public CurrencyRatesDto getListOfCurrencies() {
        return currencyConverterService.getConversionRatesFor("EU");
    }

}
