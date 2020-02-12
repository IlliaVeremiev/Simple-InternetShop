package viel.internetshop.simple.services;

import org.jasypt.util.text.TextEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import veil.internetshop.simple.constants.Cookies;
import veil.internetshop.simple.constants.Localization;
import veil.internetshop.simple.security.DefaultUserDetails;
import veil.internetshop.simple.services.impl.DefaultAuthenticationService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAuthenticationServiceUnitTest{

    private static final String TEST_EXCEPTION_MESSAGE = "Test exception message";

    @InjectMocks
    private DefaultAuthenticationService authenticationService;

    @Mock
    private TextEncryptor textEncryptor;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthenticationException authenticationException;

    @Mock
    private Authentication authentication;

    @Mock
    private DefaultUserDetails defaultUserDetails;

    @Captor
    private ArgumentCaptor<Cookie> cookieCaptor;

    @Before
    public void setUp(){
        when(authentication.getPrincipal()).thenReturn(defaultUserDetails);
    }

    @Test
    public void shouldCommenceSendUnauthorizedError() throws IOException{
        authenticationService.commence(request, response, authenticationException);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
    }

    @Test
    public void shouldLogoutRemoveAuthenticationCookie(){
        authenticationService.logout(request, response, authentication);

        verify(response).addCookie(cookieCaptor.capture());
        assertEquals(Cookies.AUTHENTICATION_COOKIE_NAME, cookieCaptor.getValue().getName());
    }

    @Test
    public void shouldSendBadCredentialsErrorMessageOnBadCredentialsException() throws IOException{
        authenticationService.onAuthenticationFailure(request, response, new BadCredentialsException(TEST_EXCEPTION_MESSAGE));

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, Localization.BAD_CREDENTIALS_EXCEPTION);
    }

    @Test
    public void shouldSendDisabledErrorMessageOnDisabledException() throws IOException{
        authenticationService.onAuthenticationFailure(request, response, new DisabledException(TEST_EXCEPTION_MESSAGE));

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, Localization.DISABLED_EXCEPTION);
    }

    @Test
    public void shouldSetCookieOnSuccessAuthentication(){
        authenticationService.onAuthenticationSuccess(request,response,authentication);

        verify(response).addCookie(cookieCaptor.capture());
        assertEquals(Cookies.AUTHENTICATION_COOKIE_NAME, cookieCaptor.getValue().getName());
    }
}
