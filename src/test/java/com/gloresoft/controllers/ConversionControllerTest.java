package com.gloresoft.controllers;

import com.gloresoft.entity.Registration;
import com.gloresoft.entity.User;
import com.gloresoft.model.ConversionDTO;
import com.gloresoft.model.RegisterDTO;
import com.gloresoft.service.ConversionService;
import com.gloresoft.service.CurrencyService;
import com.gloresoft.service.LocationService;
import com.gloresoft.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ConversionControllerTest{

    private MockMvc mockMvc;

    @InjectMocks
    private ConversionController conversionController;

    @Mock
    private CurrencyService currencyServiceMock;

    @Mock
    private ConversionService conversionServiceMock;

    @Mock
    View mockView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(conversionController).setSingleView(mockView).build();
    }

    @Test
    public void testConvert() throws Exception {
        ConversionDTO conversionDTO = new ConversionDTO();
        User user = new User();
        user.setId(1L);

        mockMvc.perform(post("/convert").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .requestAttr("conversionDTO", conversionDTO)
                .sessionAttr("user", user))
                .andExpect(status().isOk()).andExpect(view().name("redirect:/list"));

        verify(conversionServiceMock, times(1)).convert(any(ConversionDTO.class),anyLong());
    }

    @Test
    public void testList() throws Exception {
        Registration registration = new Registration();
        registration.setName("John Smith");
        User user = new User();
        user.setRegistration(registration);
        user.setId(1L);

        mockMvc.perform(get("/list").sessionAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("currencyTypes"))
                .andExpect(model().attributeExists("conversions"))
                .andExpect(model().attributeExists("name"))
                .andExpect(view().name("main"));

    }
}
