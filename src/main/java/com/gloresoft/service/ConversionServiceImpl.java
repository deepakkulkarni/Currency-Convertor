package com.gloresoft.service;

import com.gloresoft.entity.Conversion;
import com.gloresoft.model.ConversionDTO;
import com.gloresoft.repository.ConversionRepository;
import com.gloresoft.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<Conversion> getConversions() {
        List<Conversion> conversions = conversionRepository.findAll();
        return conversions;
    }

    @Override
    public void convert(ConversionDTO conversionDTO) {
        Conversion conversion = createConversion(conversionDTO);

        BigDecimal rate = currencyRepository.getCurrencyConversion(conversionDTO);

        conversion.setRate(rate);
        conversionRepository.create(conversion);
    }

    private Conversion createConversion(ConversionDTO conversionDTO) {
        Conversion conversion = new Conversion();
        conversion.setFromCurrency(conversionDTO.getFromCurrency());
        conversion.setToCurrency(conversionDTO.getToCurrency());
        conversion.setExchangeDate(conversionDTO.getExchangeDate());
        //conversion.setRate(conversionDTO.getRate());
        return conversion;
    }
}
