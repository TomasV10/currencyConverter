package com.currencies.converter.rates;

import com.currencies.converter.currencyconverter.dto.ConversionCurrency;
import com.currencies.converter.lietuvosbankas.LBCurrenciesClient;
import lt.lb.webservices.fxrates.CcyAmtHandling;
import lt.lb.webservices.fxrates.FxRateHandling;
import lt.lb.webservices.fxrates.FxRatesHandling;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyRatesService {
    private final LBCurrenciesClient lbCurrenciesClient;
    private  RatesRepository ratesRepository;


    public CurrencyRatesService(LBCurrenciesClient lbCurrenciesClient, RatesRepository ratesRepository) {
        this.lbCurrenciesClient = lbCurrenciesClient;
        this.ratesRepository = ratesRepository;
    }

    public List<ConversionRate> getCurrentRatesFor(String currencyUnit) {
        List<ConversionRate> dataFromDB = getAllConversionRates();
        if(dataFromDB.isEmpty()){
            saveToDataBase(dataFromLB(currencyUnit));
           return getAllConversionRates();
        }else {
            return dataFromDB;
        }
    }

    public BigDecimal convertFromCurrencyToCurrency(ConversionCurrency conversionCurrency){
        List<ConversionRate>currentRatesFor = getCurrentRatesFor(conversionCurrency.getConvertFrom());
        if(conversionCurrency.getAmountOfMoney().compareTo(BigDecimal.ZERO) < 0 ) {
            throw new IllegalArgumentException("Only positive numbers, you entered " +
                    conversionCurrency.getAmountOfMoney());
        }else if(!currentRatesFor.isEmpty()){
            return (conversionCurrency.getAmountOfMoney().multiply(getRateByUnit(conversionCurrency))
                    .setScale(2, RoundingMode.HALF_UP));
        }else throw new IllegalStateException("Conversion rates for given currencies " +
                conversionCurrency.getConvertFrom() + " and " + conversionCurrency.getConvertTo() + " not found");
    }

    private BigDecimal getRateByUnit (ConversionCurrency conversionCurrency){
        return getCurrentRatesFor(conversionCurrency.getConvertFrom()).stream()
                .filter(cr -> cr.getCurrencyFrom().equals(conversionCurrency.getConvertFrom().toUpperCase()) &&
                        cr.getCurrencyTo().equals(conversionCurrency.getConvertTo().toUpperCase()) ||
                        cr.getCurrencyTo().equals(conversionCurrency.getConvertFrom().toUpperCase()) &&
                                cr.getCurrencyFrom().equals(conversionCurrency.getConvertTo().toUpperCase()))
                .map(ConversionRate::getRate)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Conversion not found " +
                        conversionCurrency.getConvertFrom()));
    }

    private List<ConversionRate> getAllConversionRates(){
        return ratesRepository.findAll();
    }
    private void saveToDataBase(FxRatesHandling allCurrencies){
        convertToEntities(allCurrencies);
    }

    private FxRatesHandling dataFromLB(String currencyUnit){
        return lbCurrenciesClient.getAllCurrencies(currencyUnit);
    }

    private void convertToEntities(FxRatesHandling rates) {
        List<FxRateHandling> conversionRates = rates.getFxRate();
        for (FxRateHandling rate : conversionRates ){
            for(CcyAmtHandling ccy : rate.getCcyAmt()){
                ConversionRate conversionRate = new ConversionRate(rate.getTp().name(), ccy.getCcy().name(),
                        ccy.getAmt(), rate.getDt().toGregorianCalendar().toZonedDateTime().toLocalDate());
                ratesRepository.save(conversionRate);
            }
        }
    }
}
