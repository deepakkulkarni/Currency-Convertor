package com.gloresoft.service;

import com.gloresoft.model.ConversionDTO;
import com.gloresoft.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<String> getTypes() {
        return currencyRepository.getCurrencyTypes();
    }

    public BigDecimal getConversionRate(ConversionDTO conversionDTO) {
        return currencyRepository.getCurrencyConversion(conversionDTO);
    }

}
