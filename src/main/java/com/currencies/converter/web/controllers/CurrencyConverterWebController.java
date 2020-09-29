package com.currencies.converter.web.controllers;

import com.currencies.converter.currencyconverter.CurrencyConverterService;
import com.currencies.converter.currencyconverter.dto.ConversionCurrency;
import com.currencies.converter.currencyconverter.dto.ConversionRatesDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/mvc/currencies")
public class CurrencyConverterWebController {

    private final CurrencyConverterService currencyConverterService;

    public CurrencyConverterWebController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @GetMapping
    public String loadHomePage(@RequestParam("baseUnit") String baseUnit, Model model) {
        ConversionRatesDto conversionRatesDto = currencyConverterService.getConversionRatesFor(baseUnit);
        model.addAttribute("conversionCurrency", new ConversionCurrency());
        model.addAttribute("conversionRates", conversionRatesDto.getRates());
        return "currencyConverter";
    }

    @PostMapping("/convert")
    public ModelAndView convert(Model model, @ModelAttribute ConversionCurrency conversionCurrency ) {
        BigDecimal convertedAmount = currencyConverterService.getAmountOfConvertedMoney(conversionCurrency);
        model.addAttribute("convertedMoney", convertedAmount);
        return new ModelAndView("result");
    }
}
