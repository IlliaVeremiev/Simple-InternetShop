package veil.internetshop.simple.data;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import veil.internetshop.simple.models.Product;
import veil.internetshop.simple.view.jsonviews.BaseCartView;

import java.math.BigDecimal;

@Data
public class CartEntityData{

    @JsonView(BaseCartView.class)
    private Long id;

    @JsonView(BaseCartView.class)
    private Product product;

    @JsonView(BaseCartView.class)
    private BigDecimal count;

    @JsonView(BaseCartView.class)
    private BigDecimal subtotal;
}
