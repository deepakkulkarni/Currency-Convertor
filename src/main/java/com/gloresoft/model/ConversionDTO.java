package com.gloresoft.model;

import java.util.Date;

public class ConversionDTO {

    private String fromCurrency;
    private String toCurrency;
    private Date exchangeDate;

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }
}
