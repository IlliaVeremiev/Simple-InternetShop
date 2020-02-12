package veil.internetshop.simple.data;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import veil.internetshop.simple.view.jsonviews.BaseCartView;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartData{

	@JsonView(BaseCartView.class)
	private List<CartEntityData> entries;

	@JsonView(BaseCartView.class)
	private BigDecimal subtotal;
}
