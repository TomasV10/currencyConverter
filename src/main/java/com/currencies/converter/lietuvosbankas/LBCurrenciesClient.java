package com.currencies.converter.lietuvosbankas;

import lt.lb.webservices.fxrates.FxRatesHandling;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currency", url = "https://www.lb.lt/webservices/FxRates/FxRates.asmx")
@Component
public interface LBCurrenciesClient {

    @GetMapping(value = "/getCurrentFxRates")
    FxRatesHandling getAllCurrencies(@RequestParam("tp") String baseCurrency);
}
