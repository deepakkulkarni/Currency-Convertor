package com.gloresoft.controllers;

import com.gloresoft.entity.User;
import com.gloresoft.model.LoginDTO;
import com.gloresoft.model.RegisterDTO;
import com.gloresoft.service.LocationService;
import com.gloresoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @Value("${error.authentication}")
    private String authenticationError = "";

    @Value("${session.expiry.time}")
    private int sessionExpiryTime;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("countries", locationService.getAllCountries());
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute final RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/check-username", method = RequestMethod.POST)
    public ResponseEntity<Boolean> isUserNameExists(HttpServletRequest request) {
        String username = request.getParameter("username");
        Boolean result = userService.isUserNameExists(username);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ModelAndView authenticate(HttpServletRequest request, @ModelAttribute final LoginDTO loginDTO) {
        if (userService.authenticate(loginDTO)) {
            User user = userService.findByName(loginDTO.getUsername());
            setSession(request, user);
            return new ModelAndView("redirect:/list");
        } else {
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("authenticationError", authenticationError);
            mav.setViewName("login");
            return mav;
        }
    }

    private void setSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(sessionExpiryTime);
    }

    @RequestMapping(value = "/invalidate", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("redirect:/");
    }
}
