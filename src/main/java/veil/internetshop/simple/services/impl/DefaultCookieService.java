package veil.internetshop.simple.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import veil.internetshop.simple.services.ContextService;
import veil.internetshop.simple.services.CookieService;

import javax.servlet.http.Cookie;

public class DefaultCookieService implements CookieService{

    @Autowired
    private ContextService contextService;

    @Override
    public void setCookie(Cookie cookie){
        contextService.currentResponse().addCookie(cookie);
    }
}
