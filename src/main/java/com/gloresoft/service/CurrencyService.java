package com.gloresoft.service;

import com.gloresoft.model.ConversionDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyService {

    List<String> getTypes();

    BigDecimal getConversionRate(final ConversionDTO conversionDTO);
}

