package com.gloresoft.repository;


import com.gloresoft.model.ConversionDTO;
import com.gloresoft.model.CurrencyDTO;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CurrencyRepositoryImpl implements CurrencyRepository {

    private final static String currency_API_BASE_URL = "http://api.fixer.io/";

    public List<String> getCurrencyTypes() {
        String currency_API_URL = currency_API_BASE_URL + "latest";

        List<String> currencyType = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        CurrencyDTO currencyDTO = restTemplate.getForObject(currency_API_URL, CurrencyDTO.class);

        currencyType.add(currencyDTO.getBaseCurrency());
        currencyDTO.getRates().keySet().forEach(e -> currencyType.add(e));
        return currencyType;
    }

    public BigDecimal getCurrencyConversion(ConversionDTO conversionDTO) {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(conversionDTO.getExchangeDate());
        String fromCurrency = conversionDTO.getFromCurrency();
        String toCurrency = conversionDTO.getToCurrency();

        String currency_API_URL = currency_API_BASE_URL + date + "?base=" + fromCurrency + "&symbols=" + toCurrency;

        RestTemplate restTemplate = new RestTemplate();
        CurrencyDTO currencyDTO = restTemplate.getForObject(currency_API_URL, CurrencyDTO.class);

        BigDecimal rate = currencyDTO.getRates().get(toCurrency);
        return rate;
    }
}
