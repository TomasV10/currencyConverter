package com.currencies.converter.rates;

import com.currencies.converter.currencyconverter.dto.ConversionCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatesRepository extends JpaRepository<ConversionRate, Long> {
    List<ConversionRate> findAllByCurrencyFrom(String currencyFrom);
}
