package viel.internetshop.simple.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import veil.internetshop.simple.utils.CookieUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CookieUtilsUnitTest{

    private static final String COOKIE_NAME = "testCookie";

    @Mock
    private HttpServletResponse mockResponse;

    @Captor
    private ArgumentCaptor<Cookie> cookieArgumentCaptor;

    @Test
    public void shouldSetCookieByNameWithZeroLifetime(){
        CookieUtils.removeCookie(mockResponse, COOKIE_NAME);
        verify(mockResponse).addCookie(cookieArgumentCaptor.capture());
        Cookie cookie = cookieArgumentCaptor.getValue();

        assertEquals(COOKIE_NAME, cookie.getName());
        assertEquals(0, cookie.getMaxAge());
    }
}
