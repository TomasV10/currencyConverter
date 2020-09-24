package com.currencies.converter.rates;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import com.currencies.converter.lietuvosbankas.LBCurrenciesClient;
import lt.lb.webservices.fxrates.CcyAmtHandling;
import lt.lb.webservices.fxrates.FxRatesHandling;
import org.springframework.stereotype.Component;

@Component
public class CurrencyRatesService {
    private final LBCurrenciesClient lbCurrenciesClient;
    private  RatesRepository ratesRepository;


    public CurrencyRatesService(LBCurrenciesClient lbCurrenciesClient, RatesRepository ratesRepository) {
        this.lbCurrenciesClient = lbCurrenciesClient;
        this.ratesRepository = ratesRepository;
    }

    public List<CurrencyRate> getCurrentRatesFor(String currencyUnit) {
        saveToDB(currencyUnit);
        return getAllCurrenciesDB();
    }



    public BigDecimal convertFromEurOrTo(String fromCurrencyUnit, String toCurrencyUnit,
                                         BigDecimal amountOfMoneyToConvert){
        if(fromCurrencyUnit.equals("EUR")){
            return (amountOfMoneyToConvert.multiply(getRateByUnit(toCurrencyUnit)));
        }else {
            return (amountOfMoneyToConvert.divide(getRateByUnit(fromCurrencyUnit), 2, RoundingMode.UP));
        }
    }

    public BigDecimal getRateByUnit(String currencyUnit){
        return getConversionRates().stream()
                .filter(cr -> cr.getCurrencyUnit().equals(currencyUnit))
                .map(ConversionRate::getRate)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Conversion not found " + currencyUnit));

    }

    public CurrencyRate getRateById(Long id){
        return ratesRepository.getOne(id);
    }

    public List<ConversionRate>getConversionRates(){
        return getAllCurrenciesDB().stream()
                .map(CurrencyRate::getConversionRate)
                .flatMap(List::stream)
                .collect(toList());
    }

    private List<CurrencyRate>saveToDB(String currencyUnit){
        FxRatesHandling allCurrencies = lbCurrenciesClient.getAllCurrencies(currencyUnit);
        return ratesRepository.saveAll(convertToEntities(allCurrencies));
    }

    private List<CurrencyRate>getAllCurrenciesDB(){
        return ratesRepository.findAll();
    }

    private List<CurrencyRate> convertToEntities(FxRatesHandling rates) {
        return rates.getFxRate().stream()
                .map(rate -> new CurrencyRate(rate.getTp().name(), rate.getDt().toGregorianCalendar().toZonedDateTime()
                        .toLocalDate(), toConversionRate(rate.getCcyAmt())))
                .collect(toList());
    }

    private List<ConversionRate> toConversionRate(List<CcyAmtHandling> ccyAmtHandling) {
        return ccyAmtHandling.stream()
                .map(ccy -> new ConversionRate(ccy.getCcy().name(), ccy.getAmt()))
                .collect(toList());
    }
}
