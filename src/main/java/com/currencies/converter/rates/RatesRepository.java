package com.currencies.converter.rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RatesRepository extends JpaRepository<CurrencyRate, Long> {
}
