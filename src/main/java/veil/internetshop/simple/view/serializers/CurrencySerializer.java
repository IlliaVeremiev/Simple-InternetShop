package veil.internetshop.simple.view.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Currency serializer. Shows {@link BigDecimal} with two numbers after point
 *
 * @author - Ilya Veremyev
 */
public class CurrencySerializer extends JsonSerializer<BigDecimal>{

	@Override
	public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException{
		BigDecimal scale = value.setScale(2, BigDecimal.ROUND_UP);
		jgen.writeString(scale.toString());
	}
}
