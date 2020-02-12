package veil.internetshop.simple.services;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.ServletRequestListener;

public interface AuthenticationService extends AuthenticationEntryPoint, AuthenticationSuccessHandler,
        AuthenticationFailureHandler, LogoutHandler, ServletRequestListener{

}
