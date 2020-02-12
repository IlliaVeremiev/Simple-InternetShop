package veil.internetshop.simple.services;

import veil.internetshop.simple.data.CartData;
import veil.internetshop.simple.models.Cart;
import veil.internetshop.simple.models.CartEntry;

import java.math.BigDecimal;

public interface CartService {

	Cart getById(Long id);

	Cart getCurrentCart();

	Cart createCart();

	CartData calculateCartData(Cart cart);

	CartEntry setCurrentCartProductCount(Long productId, BigDecimal count);

	CartEntry addCurrentCartProductCount(Long id, BigDecimal count);

    void deleteProduct(Long id);
}
