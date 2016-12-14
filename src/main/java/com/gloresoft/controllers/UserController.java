package com.gloresoft.controllers;

import com.gloresoft.bean.Login;
import com.gloresoft.bean.Register;
import com.gloresoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ModelAndView authenticate(@ModelAttribute Login login) {
        if (userService.authenticate(login))
            return new ModelAndView("main");
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute Register register) {
        userService.register(register);
        return new ModelAndView("main");
    }
}
