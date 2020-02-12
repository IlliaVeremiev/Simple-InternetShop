package viel.internetshop.simple.view.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import veil.internetshop.simple.view.serializers.ProductImagePathSerializer;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductImagePathSerializerUnitTest{

    private static final String PRODUCT_PHOTO_PREFIX = "/img/";

    @InjectMocks
    private ProductImagePathSerializer productImagePathSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(productImagePathSerializer, "productPhotoPrefix", PRODUCT_PHOTO_PREFIX);
    }

    @Test
    public void shouldAddPhotoPathPrefix() throws IOException{
        String baseTest = "testText";
        productImagePathSerializer.serialize(baseTest, jsonGenerator, serializerProvider);

        verify(jsonGenerator).writeString(PRODUCT_PHOTO_PREFIX + baseTest);
    }
}
