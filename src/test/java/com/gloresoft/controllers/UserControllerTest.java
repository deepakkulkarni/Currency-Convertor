package com.gloresoft.controllers;

import com.gloresoft.entity.User;
import com.gloresoft.model.LoginDTO;
import com.gloresoft.model.RegisterDTO;
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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userServiceMock;

    @Mock
    private LocationService locationServiceMock;

    @Mock
    View mockView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setSingleView(mockView).build();
    }

    @Test
    public void shouldShowLoginPage() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("login"));
    }

    @Test
    public void testRegisterGET() throws Exception {
        mockMvc.perform(get("/register")).andExpect(status().isOk())
                .andExpect(model().attributeExists("countries"))
                .andExpect(view().name("register"));

        verify(locationServiceMock, times(1)).getAllCountries();
    }

    @Test
    public void testIsUserNameExistsSuccessful() throws Exception {
        when(userServiceMock.isUserNameExists(anyString())).thenReturn(true);

        MvcResult result = mockMvc.perform(post("/check-username").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .requestAttr("username", anyString()))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();

        Assert.assertEquals("true", content);
    }

    @Test
    public void testIsUserNameExistsFailure() throws Exception {
        when(userServiceMock.isUserNameExists(anyString())).thenReturn(false);

        MvcResult result = mockMvc.perform(post("/check-username").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .requestAttr("username", anyString()))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();

        Assert.assertEquals("false", content);
    }

    @Test
    public void testAuthenticateFailure() throws Exception {

        when(userServiceMock.authenticate(any(LoginDTO.class))).thenReturn(false);

        mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk()).andExpect(model().attributeExists("authenticationError")).andExpect(view().name("login"));
    }

    @Test
    public void testAuthenticateSuccess() throws Exception {

        when(userServiceMock.authenticate(any(LoginDTO.class))).thenReturn(true);

        User user = new User();
        when(userServiceMock.findByName(anyString())).thenReturn(user);

        mockMvc.perform(post("/authenticate").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("user",user))
                .andExpect(view().name("redirect:/list")).andReturn();
    }

    @Test
    public void testLogout() throws Exception {

        mockMvc.perform(get("/invalidate").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk()).andExpect(view().name("redirect:/"));

    }

    /*private LoginDTO createLoginDTO() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("john");
        loginDTO.setPassword("123456");
        return loginDTO;
    }*/

    @Test
    public void testRegisterPOST() throws Exception {
        RegisterDTO registerDTO = createRegisterDTO();
        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .requestAttr("registerDTO", registerDTO))
                .andExpect(status().isOk()).andExpect(view().name("login"));

        verify(userServiceMock, times(1)).register(any(RegisterDTO.class));
    }

    private RegisterDTO createRegisterDTO() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setName("John Smith");
        registerDTO.setUsername("john");
        registerDTO.setPassword("123456");
        registerDTO.setEmail("john.smith@company.com");
        registerDTO.setAddress("Ocean Avenue Apt 6B Brooklyn");
        registerDTO.setPin("11211");
        registerDTO.setCity("New York");
        registerDTO.setCountry("USA");
        return registerDTO;
    }
}
