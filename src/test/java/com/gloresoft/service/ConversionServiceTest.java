package com.gloresoft.service;

import com.gloresoft.entity.Conversion;
import com.gloresoft.entity.User;
import com.gloresoft.model.ConversionDTO;
import com.gloresoft.repository.ConversionRepository;
import com.gloresoft.repository.CurrencyRepository;
import com.gloresoft.repository.RegistrationRepository;
import com.gloresoft.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConversionServiceTest {

    @Mock
    private ConversionRepository conversionRepositoryMock;

    @Mock
    private RegistrationRepository registrationRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private CurrencyRepository currencyRepositoryMock;

    @InjectMocks
    private ConversionServiceImpl conversionService;

    @Test
    public void testGetConversions() {

        conversionService.getConversions(anyLong());

        verify(conversionRepositoryMock, times(1)).findAllByUserId(anyLong());
    }

    @Test
    public void testConvert() {
        User user = new User();
        ConversionDTO conversionDTO = new ConversionDTO();
        conversionDTO.setFromCurrency("EUR");
        conversionDTO.setToCurrency("USD");
        conversionDTO.setExchangeDate(new Date());

        when(userRepositoryMock.findById(anyLong())).thenReturn(user);

        when(currencyRepositoryMock.getCurrencyConversion(any(ConversionDTO.class))).thenReturn(any(BigDecimal.class));

        conversionService.convert(conversionDTO, 1L);

        verify(conversionRepositoryMock, times(1)).merge(any(Conversion.class));
    }


}
