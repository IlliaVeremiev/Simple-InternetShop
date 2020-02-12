package veil.internetshop.simple.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ImportResource("classpath:applicationContext.xml")
public class MvcConfig implements WebMvcConfigurer{

    @Value("${product.photo.path}")
    private String productPhotoPath;

    @Value("${product.test.photo.path}")
    private String productTestPhotoPath;

    @Value("${product.photo.prefix}")
    private String productPhotoPrefix;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(productPhotoPrefix + "**")
                .addResourceLocations(productPhotoPath, productTestPhotoPath);
    }
}
