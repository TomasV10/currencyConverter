package com.currencies.converter.rates;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "currency_rate")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "currency_unit")
    private String currencyUnit;
    @Column(name = "date")
    private LocalDate date;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_rate_id")
    private List<ConversionRate> conversionRate;

    public CurrencyRate() {
    }

    public CurrencyRate(String currencyUnit, LocalDate date, List<ConversionRate> conversionRate) {
        this.currencyUnit = currencyUnit;
        this.date = date;
        this.conversionRate = conversionRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ConversionRate> getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(List<ConversionRate> conversionRate) {
        this.conversionRate = conversionRate;
    }
}
