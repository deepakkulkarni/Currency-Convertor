package com.gloresoft.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDTO {

    private String baseCurrency;
    private String date;
    private Map<String, BigDecimal> rates = new HashMap<>();

    @JsonCreator
    public CurrencyDTO(@JsonProperty("base") String baseCurrency, @JsonProperty("date") String date, @JsonProperty("rates") Map<String, BigDecimal> rates) {
        this.baseCurrency = baseCurrency;
        this.date = date;
        this.rates = rates;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getDate() {
        return date;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }
}