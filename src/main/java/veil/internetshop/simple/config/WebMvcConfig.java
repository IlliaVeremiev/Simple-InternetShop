package veil.internetshop.simple.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import veil.internetshop.simple.constants.View;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

    @Resource
    private List<String> viewsList;

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        for(String view : viewsList){
            registry.addViewController(view).setViewName(View.INDEX_PAGE);
        }
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerCustomizer(){
        return container -> container.addErrorPages(
                new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
    }
}
