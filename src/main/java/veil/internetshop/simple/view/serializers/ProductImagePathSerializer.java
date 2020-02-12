package veil.internetshop.simple.view.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class ProductImagePathSerializer extends JsonSerializer<String>{

    @Value("${product.photo.prefix}")
    private String productPhotoPrefix;

    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException{
        jgen.writeString(productPhotoPrefix + value);
    }
}
