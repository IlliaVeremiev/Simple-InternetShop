package veil.internetshop.simple.services.impl;

import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;
import veil.internetshop.simple.constants.Cookies;
import veil.internetshop.simple.constants.Localization;
import veil.internetshop.simple.security.DefaultUserDetails;
import veil.internetshop.simple.services.AuthenticationService;
import veil.internetshop.simple.utils.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DefaultAuthenticationService extends RequestContextListener implements AuthenticationService{

    @Autowired
    private TextEncryptor textEncryptor;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        DefaultUserDetails userDetails = (DefaultUserDetails)authentication.getPrincipal();
        String code = textEncryptor.encrypt(userDetails.getUsername());
        Cookie cookie = new Cookie(Cookies.AUTHENTICATION_COOKIE_NAME, code);
        cookie.setMaxAge(Cookies.AUTHENTICATION_COOKIE_LIFETIME);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        updateRequestContextHolder(request, response);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        CookieUtils.removeCookie(response, Cookies.AUTHENTICATION_COOKIE_NAME);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException{
        if(exception.getClass() == BadCredentialsException.class){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Localization.BAD_CREDENTIALS_EXCEPTION);
        }else if(exception.getClass() == DisabledException.class){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Localization.DISABLED_EXCEPTION);
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Localization.AUTHENTICATION_EXCEPTION);
        }
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
    }

    private void updateRequestContextHolder(HttpServletRequest request, HttpServletResponse response){
        ServletRequestAttributes attributes = new ServletRequestAttributes(request, response);
        RequestContextHolder.setRequestAttributes(attributes);
    }
}
