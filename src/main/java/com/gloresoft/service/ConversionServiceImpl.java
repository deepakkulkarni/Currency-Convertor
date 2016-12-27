package com.gloresoft.service;

import com.gloresoft.entity.Conversion;
import com.gloresoft.entity.User;
import com.gloresoft.model.ConversionDTO;
import com.gloresoft.repository.ConversionRepository;
import com.gloresoft.repository.CurrencyRepository;
import com.gloresoft.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Conversion> getConversions(Long Id) {
        return conversionRepository.findAllByUserId(Id);
    }

    @Override
    public void convert(ConversionDTO conversionDTO, Long Id) {
        User user =  userRepository.findById(Id);

        BigDecimal rate = currencyRepository.getCurrencyConversion(conversionDTO);

        Conversion conversion = createConversion(conversionDTO);
        conversion.setRate(rate);
        conversion.setUser(user);

        conversionRepository.merge(conversion);
    }

    private Conversion createConversion(ConversionDTO conversionDTO) {
        Conversion conversion = new Conversion();
        conversion.setFromCurrency(conversionDTO.getFromCurrency());
        conversion.setToCurrency(conversionDTO.getToCurrency());
        conversion.setExchangeDate(conversionDTO.getExchangeDate());
        return conversion;
    }
}
