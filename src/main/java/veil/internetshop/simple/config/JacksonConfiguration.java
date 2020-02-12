package veil.internetshop.simple.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageImpl;
import veil.internetshop.simple.view.serializers.PageSerializer;

@Configuration
public class JacksonConfiguration{

    @Bean
    public Module jacksonPageWithJsonViewModule(){
        SimpleModule module = new SimpleModule("klkl-page-with-jsonview", Version.unknownVersion());
        module.addSerializer(PageImpl.class, new PageSerializer());
        return module;
    }
}
