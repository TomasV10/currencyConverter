package com.currencies.converter.rates;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.List;

import com.currencies.converter.lietuvosbankas.LBCurrenciesClient;
import lt.lb.webservices.fxrates.CcyAmtHandling;
import lt.lb.webservices.fxrates.FxRatesHandling;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRatesService {

    private final LBCurrenciesClient lbCurrenciesClient;

    public CurrencyRatesService(LBCurrenciesClient lbCurrenciesClient) {
        this.lbCurrenciesClient = lbCurrenciesClient;
    }

    public List<CurrencyRate> getCurrentRatesFor(String currencyUnit) {


        FxRatesHandling allCurrencies = lbCurrenciesClient.getAllCurrencies(currencyUnit);
        return convertToEntities(allCurrencies);
    }

    private List<CurrencyRate> convertToEntities(FxRatesHandling rates) {
        return rates.getFxRate().stream()
                .map(rate -> new CurrencyRate(rate.getTp().name(), rate.getDt().toGregorianCalendar().toZonedDateTime().toLocalDate(), toConversionRate(rate.getCcyAmt())))
                .collect(toList());
    }

    private List<ConversionRate> toConversionRate(List<CcyAmtHandling> ccyAmtHandling) {
        return ccyAmtHandling.stream()
                .map(ccy -> new ConversionRate(ccy.getCcy().name(), ccy.getAmt()))
                .collect(toList());
    }
}
