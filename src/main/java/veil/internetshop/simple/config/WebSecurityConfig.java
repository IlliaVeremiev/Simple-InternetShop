package veil.internetshop.simple.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import veil.internetshop.simple.filters.AuthCookieFilter;
import veil.internetshop.simple.services.impl.DefaultAuthenticationService;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DefaultAuthenticationService authenticationService;

    @Autowired
    private AuthCookieFilter authCookieFilter;

    @Resource
    private List<String> resourceList;

    @Resource
    private List<String> viewsList;

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers(resourceList.toArray(new String[0]));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        http
                .authorizeRequests()
                .antMatchers("/api/categories/**").permitAll()
                .antMatchers("/api/products/**").permitAll()
                .antMatchers("/api/orders/**").permitAll()
                .antMatchers("/api/bundle/**").permitAll()
                .antMatchers("/api/users/**").permitAll()
                .antMatchers("/api/cart/**").authenticated();

        for(String view : viewsList){
            http.authorizeRequests().antMatchers(view).permitAll();
        }

        http.formLogin().permitAll()
                .successHandler(authenticationService)
                .failureHandler(authenticationService);

        http.exceptionHandling().authenticationEntryPoint(authenticationService);

        http.logout().permitAll().addLogoutHandler(authenticationService);

        http.addFilterBefore(authCookieFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests().antMatchers("/**").authenticated();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}