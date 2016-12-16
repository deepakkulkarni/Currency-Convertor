package com.gloresoft.controllers;

import com.gloresoft.bean.Login;
import com.gloresoft.bean.Register;
import com.gloresoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Stream;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        Stream<Cookie> authenticate = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("authenticate"));
        authenticate.forEach(cookie -> cookie.setMaxAge(0));
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ModelAndView authenticate(@ModelAttribute Login login, HttpServletResponse response) {
        if (userService.authenticate(login)) {
            addCookie(login, response);
            return new ModelAndView("main");
        }
        return new ModelAndView("login");
    }

    private void addCookie(@ModelAttribute Login login, HttpServletResponse response) {
        Cookie cookie = new Cookie("authenticate", login.getUsername());
        cookie.setMaxAge(5 * 60);
        response.addCookie(cookie);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute Register register) {
        userService.register(register);
        return new ModelAndView("main");
    }
}
