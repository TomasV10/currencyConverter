package com.currencies.converter.web.controllers;

import com.currencies.converter.currencyconverter.CurrencyConverterService;
import com.currencies.converter.currencyconverter.dto.ConversionCurrency;
import com.currencies.converter.currencyconverter.dto.ConversionRatesDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

@Controller
@RequestMapping("/mvc/currencies")
public class CurrencyConverterWebController {

    private final CurrencyConverterService currencyConverterService;

    public CurrencyConverterWebController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }
    @GetMapping
    public String loadHomePage(Model model) {
        ConversionRatesDto conversionRatesDto = currencyConverterService.getPossibleCurrencyUnits();
        model.addAttribute("conversionCurrency", new ConversionCurrency());
        model.addAttribute("conversionRates", conversionRatesDto);
        return "currencyConverter";
    }

    @PostMapping("/convert")
    public ModelAndView convert(Model model, @ModelAttribute ConversionCurrency conversionCurrency ) {
        validate(conversionCurrency);
        BigDecimal convertedAmount = currencyConverterService.getAmountOfConvertedMoney(conversionCurrency);
        model.addAttribute("convertedMoney", convertedAmount);
        return new ModelAndView("result");
    }

    private void validate(ConversionCurrency conversionCurrency) {
        if(conversionCurrency.getAmount().compareTo(ZERO) < 0){
            throw new IllegalArgumentException("Amount can not be negative");
        }
    }

}
