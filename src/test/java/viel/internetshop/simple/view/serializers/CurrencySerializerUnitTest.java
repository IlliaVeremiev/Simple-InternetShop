package viel.internetshop.simple.view.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import veil.internetshop.simple.view.serializers.CurrencySerializer;

import java.io.IOException;
import java.math.BigDecimal;

import static org.mockito.Mockito.verify;

@JsonTest
@RunWith(MockitoJUnitRunner.class)
public class CurrencySerializerUnitTest{

    @InjectMocks
    private CurrencySerializer currencySerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @Test
    public void shouldSerializerAddZerosEvenOnZero() throws IOException{
        String input = "0";
        String expected = "0.00";
        currencySerializer.serialize(new BigDecimal(input), jsonGenerator, serializerProvider);

        verify(jsonGenerator).writeString(expected);
    }

    @Test
    public void shouldSerializerAlwaysRoundUp() throws IOException{
        String input = "1.00000001";
        String expected = "1.01";
        currencySerializer.serialize(new BigDecimal(input), jsonGenerator, serializerProvider);

        verify(jsonGenerator).writeString(expected);
    }
}
