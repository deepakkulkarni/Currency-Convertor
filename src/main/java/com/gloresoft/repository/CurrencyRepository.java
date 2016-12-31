package com.gloresoft.repository;

import com.gloresoft.model.ConversionDTO;
import java.math.BigDecimal;
import java.util.List;

public interface CurrencyRepository {

    List<String> getCurrencyTypes();

    BigDecimal getCurrencyConversion(ConversionDTO conversionDTO);

}
