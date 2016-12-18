package com.gloresoft.controllers;

import com.gloresoft.model.ConversionDTO;
import com.gloresoft.service.ConversionService;
import com.gloresoft.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConversionController {

    @Autowired
    public ConversionService conversionService;

    @Autowired
    public CurrencyService currencyService;

    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public ModelAndView convert(@ModelAttribute ConversionDTO conversionDTO) {
        conversionService.convert(conversionDTO);
        return new ModelAndView("redirect:/list");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("currencyTypes", currencyService.getTypes());
        mav.addObject("conversions", conversionService.getConversions());
        mav.setViewName("main");
        return mav;
    }

}
