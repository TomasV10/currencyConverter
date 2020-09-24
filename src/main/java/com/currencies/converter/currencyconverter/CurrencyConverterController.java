package com.currencies.converter.currencyconverter;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.currencies.converter.currencyconverter.dto.ConversionRateDto;
import com.currencies.converter.currencyconverter.dto.CurrencyRateDto;
import com.currencies.converter.currencyconverter.dto.CurrencyRatesDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.math.BigDecimal;
import java.util.List;

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

    @GetMapping("/{id}")
    public CurrencyRateDto getCurrencyById(@PathVariable Long id){
        return currencyConverterService.getCurrencyRateById(id);
    }

    @GetMapping("/unit")
    public List<ConversionRateDto> getListOfConversionRates(){
        return currencyConverterService.getAllConversionRates();
    }

    @GetMapping("/rateByUnit/{currencyUnit}")
    public BigDecimal getConversionRateByUnit(@PathVariable("currencyUnit") String currencyUnit){
        return currencyConverterService.getRateByCurrencyUnit(currencyUnit);
    }

    @GetMapping("/amount/{fromCurrencyUnit}/{toCurrencyUnit}/{amountOfMoneyToConvert}")
    public BigDecimal getConvertedAmount(@PathVariable("fromCurrencyUnit") String fromCurrencyUnit,
                                         @PathVariable("toCurrencyUnit") String toCurrencyUnit,
                                         @PathVariable("amountOfMoneyToConvert") BigDecimal amountOfMoney){
        return currencyConverterService.getAmountOfConvertedMoney(fromCurrencyUnit, toCurrencyUnit, amountOfMoney);
    }
}
