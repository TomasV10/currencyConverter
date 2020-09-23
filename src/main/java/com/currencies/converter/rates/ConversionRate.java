package com.currencies.converter.rates;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "conversion_rate")
public class ConversionRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "currency_unit")
    private String currencyUnit;
    @Column(name = "rate")
    private BigDecimal rate;
    @ManyToOne(fetch = FetchType.LAZY)
    private CurrencyRate currencyRate;

    public ConversionRate() {
    }

    public ConversionRate(String currencyUnit, BigDecimal rate) {
        this.currencyUnit = currencyUnit;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CurrencyRate getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(CurrencyRate currencyRate) {
        this.currencyRate = currencyRate;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
