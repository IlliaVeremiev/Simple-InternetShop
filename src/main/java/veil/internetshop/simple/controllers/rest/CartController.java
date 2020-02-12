package veil.internetshop.simple.controllers.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import veil.internetshop.simple.data.CartData;
import veil.internetshop.simple.models.Cart;
import veil.internetshop.simple.services.CartService;
import veil.internetshop.simple.view.jsonviews.BaseCartView;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cart")
public class CartController{

    @Autowired
    private CartService cartService;

    /**
     * Returns current cart data
     *
     * @return current cart
     */
    @GetMapping
    @JsonView(BaseCartView.class)
    public CartData getCurrentCart(){
        Cart currentCart = cartService.getCurrentCart();
        return cartService.calculateCartData(currentCart);
    }

    /**
     * Sets <b>count</b> piece of <b>productId</b> at current cart
     *
     * @param id    - product id
     * @param count - product count
     */
    @PutMapping("/{productId}")
    public void updateProductCount(@PathVariable("productId") Long id,
                                   @RequestParam(value = "count" , defaultValue = "1") BigDecimal count){
        cartService.setCurrentCartProductCount(id, count);
    }

    /**
     * Adds <b>count</b> piece of <b>productId</b> at current cart
     *
     * @param id    - product id
     * @param count - product count
     */
    @PostMapping("/{productId}")
    public void addProductCount(@PathVariable("productId") Long id,
                                @RequestParam(value = "count" , defaultValue = "1") BigDecimal count){
        cartService.addCurrentCartProductCount(id, count);
    }

    /**
     * Removes <b>id</b> product from current cart
     *
     * @param id - product id
     */
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") Long id){
        cartService.deleteProduct(id);
    }
}
