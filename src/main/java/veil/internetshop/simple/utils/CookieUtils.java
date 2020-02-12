package veil.internetshop.simple.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public abstract class CookieUtils{

    public static void removeCookie(HttpServletResponse response, String cookieName){
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
