package com.gloresoft.controllers;

import com.gloresoft.model.LoginDTO;
import com.gloresoft.model.RegisterDTO;
import com.gloresoft.service.CurrencyService;
import com.gloresoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public CurrencyService currencyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ModelAndView authenticate(HttpServletRequest request, @ModelAttribute LoginDTO loginDTO) {
        ModelAndView mav = new ModelAndView();

        if (userService.authenticate(loginDTO)) {
            request.getSession().setAttribute("user", loginDTO.getUsername());
            mav.addObject("currencyTypes", currencyService.getTypes());
            mav.setViewName("main");
            return mav;
        }

        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "/invalidate", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return new ModelAndView("redirect:/");
    }
}
