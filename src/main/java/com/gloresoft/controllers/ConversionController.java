package com.gloresoft.controllers;

import com.gloresoft.entity.User;
import com.gloresoft.model.ConversionDTO;
import com.gloresoft.service.ConversionService;
import com.gloresoft.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ConversionController {

    @Autowired
    public ConversionService conversionService;

    @Autowired
    public CurrencyService currencyService;

    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public ModelAndView convert(HttpServletRequest request, @ModelAttribute ConversionDTO conversionDTO) {
        User user = (User)request.getSession().getAttribute("user");
        conversionService.convert(conversionDTO, user.getId());
        return new ModelAndView("redirect:/list");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        ModelAndView mav = new ModelAndView();
        mav.addObject("currencyTypes", currencyService.getTypes());
        mav.addObject("conversions", conversionService.getConversions(user.getId()));
        mav.addObject("name", user.getRegistration().getName());
        mav.setViewName("main");
        return mav;
    }

}
