package com.gloresoft.configuration;

import com.gloresoft.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SessionInterceptor extends HandlerInterceptorAdapter {

    private static List <String>exclusionList = new ArrayList();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return validateSession(request, response);
    }

    private boolean validateSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (exclusionList.contains(path))
            return true;

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("redirect:/");
            return false;
        }
        return true;
    }

    static {
        exclusionList.add("/");
        exclusionList.add("/login");
        exclusionList.add("/invalidate");
        exclusionList.add("/register");
        exclusionList.add("/authenticate");
        exclusionList.add("/check-username");
        exclusionList.add("/error");
    }
}
