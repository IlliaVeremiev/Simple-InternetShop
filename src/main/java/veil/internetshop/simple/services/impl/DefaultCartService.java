package veil.internetshop.simple.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import veil.internetshop.simple.data.CartData;
import veil.internetshop.simple.data.CartEntityData;
import veil.internetshop.simple.exceptions.service.ResourceNotFoundException;
import veil.internetshop.simple.models.Cart;
import veil.internetshop.simple.models.CartEntry;
import veil.internetshop.simple.models.Product;
import veil.internetshop.simple.models.User;
import veil.internetshop.simple.repositories.CartEntryRepository;
import veil.internetshop.simple.repositories.CartRepository;
import veil.internetshop.simple.services.CartService;
import veil.internetshop.simple.services.ProductService;
import veil.internetshop.simple.services.UserService;
import veil.internetshop.simple.validators.BiggerThan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultCartService implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartEntryRepository cartEntryRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public Cart getById(Long id){
        return cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart with id '" + id + "'not found"));
    }

    private CartEntry createCartEntry(Cart cart, Long productId){
        Product product = productService.getProduct(productId);
        CartEntry cartEntry = new CartEntry();
        cartEntry.setProduct(product);
        cartEntry.setCount(BigDecimal.ZERO);
        cartEntry.setCart(cart);
        return cartEntryRepository.save(cartEntry);
    }

    @Override
    public Cart getCurrentCart(){
        User user = userService.getCurrentUser();
        if(Objects.nonNull(user)){
            return user.getCart();
        }else{
            throw new ResourceNotFoundException("Full authentication required for this operation");
        }
    }

    @Override
    public Cart createCart(){
        return cartRepository.save(new Cart());
    }

    @Override
    public CartData calculateCartData(Cart cart){
        CartData cartData = new CartData();
        List<CartEntityData> entityDataList = new ArrayList<>();
        for(CartEntry entry : cart.getCartEntries()){
            CartEntityData data = new CartEntityData();
            data.setProduct(entry.getProduct());
            data.setCount(entry.getCount());
            data.setId(entry.getId());
            data.setSubtotal(productService.calculateProductPrice(entry.getProduct(), entry.getCount()));
            entityDataList.add(data);
        }
        cartData.setEntries(entityDataList);
        cartData.setSubtotal(entityDataList.stream().map(CartEntityData::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return cartData;
    }

    @Override
    public CartEntry setCurrentCartProductCount(Long productId, @BiggerThan("0") BigDecimal count){
        Product product = productService.getProduct(productId);
        Cart cart = getCurrentCart();
        CartEntry cartEntry = findEntryByProduct(cart, product).orElseGet(() -> createCartEntry(cart, productId));
        cartEntry.setCount(count);
        return cartEntryRepository.save(cartEntry);
    }

    @Override
    public CartEntry addCurrentCartProductCount(Long id, @BiggerThan("0") BigDecimal count){
        Product product = productService.getProduct(id);
        Cart cart = getCurrentCart();
        CartEntry cartEntry = findEntryByProduct(cart, product).orElseGet(() -> createCartEntry(cart, id));
        cartEntry.setCount(cartEntry.getCount().add(count));
        return cartEntryRepository.save(cartEntry);
    }

    @Override
    public void deleteProduct(Long id){
        Product product = productService.getProduct(id);
        Cart cart = getCurrentCart();
        findEntryByProduct(cart, product).ifPresent(cartEntryRepository::delete);
    }

    private Optional<CartEntry> findEntryByProduct(Cart cart, Product product){
        return cart.getCartEntries()
                .stream()
                .peek(e -> System.out.println(e.getProduct().getId() + " " + product.getId() + " " + e.getProduct().getId().equals(product.getId())))
                .filter(e -> e.getProduct().getId().equals(product.getId()))
                .findFirst();
    }
}
