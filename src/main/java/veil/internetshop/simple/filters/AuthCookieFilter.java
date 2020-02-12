package veil.internetshop.simple.filters;

import org.apache.log4j.Logger;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.util.AntPathMatcher;
import veil.internetshop.simple.constants.Cookies;
import veil.internetshop.simple.services.UserService;
import veil.internetshop.simple.utils.CookieUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * Selects {@value Cookies#AUTHENTICATION_COOKIE_NAME} cookie value from request
 * and inject related user details to spring security context holder
 *
 * @author Illia_Veremiev
 */
public class AuthCookieFilter implements Filter{

    private static final Logger log = Logger.getLogger(AuthCookieFilter.class);

    @Autowired
    private TextEncryptor textEncryptor;

    @Autowired
    private UserService userService;

    @Autowired
    private AntPathMatcher antPathMatcher;

    @Autowired
    private UserDetailsChecker userDetailsChecker;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
        try{
            getCookies(httpRequest).ifPresent(cookies -> getCookie(cookies, Cookies.AUTHENTICATION_COOKIE_NAME).ifPresent(this::setUserDetailsFromCookie));
        }catch(Exception e){
            log.warn("Exception " + e.getClass().getSimpleName() + "was thrown when decrypting auth cookie(" + Cookies.AUTHENTICATION_COOKIE_NAME + ")");
            CookieUtils.removeCookie(httpResponse, Cookies.AUTHENTICATION_COOKIE_NAME);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Optional<Cookie[]> getCookies(HttpServletRequest request){
        return Optional.ofNullable(request.getCookies());
    }

    private Optional<Cookie> getCookie(Cookie[] cookies, String name){
        return Arrays.stream(cookies).filter(c -> c.getName().equals(name)).findFirst();
    }

    private void setUserDetailsFromCookie(Cookie cookie){
        String decryptedCookieValue = textEncryptor.decrypt(cookie.getValue());
        UserDetails userDetails = userService.loadUserByUsername(decryptedCookieValue);
        userDetailsChecker.check(userDetails);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
    }
}