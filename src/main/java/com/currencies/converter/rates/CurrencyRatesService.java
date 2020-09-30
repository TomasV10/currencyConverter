package com.currencies.converter.rates;

import com.currencies.converter.lietuvosbankas.LBCurrenciesClient;
import lt.lb.webservices.fxrates.CcyAmtHandling;
import lt.lb.webservices.fxrates.FxRateHandling;
import lt.lb.webservices.fxrates.FxRatesHandling;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.ONE;
import static java.math.RoundingMode.HALF_UP;

@Component
public class CurrencyRatesService {
    private static final int CURRENCY_FROM_INDEX = 0;
    private static final int CURRENCY_TO_INDEX = 1;
    private final LBCurrenciesClient lbCurrenciesClient;
    private  RatesRepository ratesRepository;


    public CurrencyRatesService(LBCurrenciesClient lbCurrenciesClient, RatesRepository ratesRepository) {
        this.lbCurrenciesClient = lbCurrenciesClient;
        this.ratesRepository = ratesRepository;
    }

    public List<String> getAvailableCurrencyUnits() {
        List<String> availableCurrencyUnits = ratesRepository.listAvailableCurrencyUnits();
        if (availableCurrencyUnits.isEmpty()) {
            saveToDataBase(retrieveAvailableCurrencyRatesFromLB());
            return ratesRepository.listAvailableCurrencyUnits();
        }
        return availableCurrencyUnits;
    }

    public BigDecimal convertFromCurrencyToCurrency(String currencyFrom, String currencyTo, BigDecimal amount) {
        return findConversionRate(currencyFrom, currencyTo)
                .map(rate -> amount.multiply(rate.getRate()))
                .or(() -> findConversionRate(currencyTo, currencyFrom).map(rate -> ONE.divide(rate.getRate(),
                        8, HALF_UP).multiply(amount)))
                .orElseThrow(() -> new IllegalArgumentException("Conversion rate not found from " +
                        currencyFrom + " to " + currencyTo));
    }

    private Optional<ConversionRate> findConversionRate(String currencyFrom, String currencyTo) {
        return ratesRepository.findByCurrencyFromAndCurrencyToOrderByDateDesc(currencyFrom, currencyTo);
    }

    private void saveToDataBase(FxRatesHandling allCurrencies) {
        allCurrencies.getFxRate().stream()
                .map(this::toConversionRate)
                .forEach(ratesRepository::save);
    }

    private ConversionRate toConversionRate(FxRateHandling rate) {
        return new ConversionRate(
                getCurrency(rate, CURRENCY_FROM_INDEX).getCcy().name(),
                getCurrency(rate, CURRENCY_TO_INDEX).getCcy().name(),
                calculateRate(rate),
                rate.getDt().toGregorianCalendar().toZonedDateTime().toLocalDate());
    }

    private BigDecimal calculateRate(FxRateHandling rate) {
        return getCurrency(rate, CURRENCY_TO_INDEX).getAmt()
                .divide(getCurrency(rate, CURRENCY_FROM_INDEX).getAmt(), 8, HALF_UP);
    }

    private CcyAmtHandling getCurrency(FxRateHandling rate, int i) {
        return rate.getCcyAmt().get(i);
    }

    private FxRatesHandling retrieveAvailableCurrencyRatesFromLB() {
        return lbCurrenciesClient.getAllCurrencies("EU");
    }
}
