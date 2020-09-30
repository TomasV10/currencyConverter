package com.currencies.converter.rates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatesRepository extends JpaRepository<ConversionRate, Long> {
    Optional<ConversionRate> findByCurrencyFromAndCurrencyToOrderByDateDesc(String currencyFrom, String currencyTo);

    @Query(value = "SELECT currency_from FROM conversion_rate union SELECT currency_to FROM conversion_rate",
            nativeQuery = true)
    List<String> listAvailableCurrencyUnits();
}
