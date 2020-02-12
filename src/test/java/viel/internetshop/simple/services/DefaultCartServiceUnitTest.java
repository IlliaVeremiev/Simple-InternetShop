package viel.internetshop.simple.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import veil.internetshop.simple.models.Cart;
import veil.internetshop.simple.models.CartEntry;
import veil.internetshop.simple.models.Product;
import veil.internetshop.simple.models.User;
import veil.internetshop.simple.repositories.CartEntryRepository;
import veil.internetshop.simple.repositories.CartRepository;
import veil.internetshop.simple.services.ProductService;
import veil.internetshop.simple.services.UserService;
import veil.internetshop.simple.services.impl.DefaultCartService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCartServiceUnitTest{

    private static final long TEST_CART_ID = 1;
    private static final long TEST_PRODUCT_ID = 1;
    private static final BigDecimal TEST_CART_ENTRY_COUNT = BigDecimal.valueOf(1);

    @InjectMocks
    private DefaultCartService defaultCartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartEntryRepository cartEntryRepository;

    @Mock
    private ProductService productService;

    @Mock
    private UserService userService;

    @Mock
    private User currentUser;

    @Mock
    private Cart cart;

    @Mock
    private CartEntry cartEntry;

    @Mock
    private Product product;

    @Mock
    private List<CartEntry> cartEntries;

    @Mock
    private Stream<CartEntry> cartEntryStream;

    @Captor
    private ArgumentCaptor<BigDecimal> countCaptor;

    @Before
    public void setUp(){
        when(userService.getCurrentUser()).thenReturn(currentUser);
        when(currentUser.getCart()).thenReturn(cart);
        when(cartRepository.findById(any())).thenReturn(Optional.of(cart));
        when(productService.getProduct(TEST_PRODUCT_ID)).thenReturn(product);
        when(cart.getCartEntries()).thenReturn(cartEntries);
        when(cartEntries.stream()).thenReturn(cartEntryStream);
        when(cartEntryStream.filter(any())).thenReturn(cartEntryStream);
        when(cartEntryStream.findFirst()).thenReturn(Optional.of(cartEntry));
        when(cartEntry.getCount()).thenReturn(TEST_CART_ENTRY_COUNT);
        when(cartEntryRepository.save(any(CartEntry.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void shouldGetCurrentCartReturnCurrentUserCart(){
        Cart currentCart = defaultCartService.getCurrentCart();

        assertEquals(cart, currentCart);
    }

    @Test
    public void shouldFindByIdCallSameRepositoryMethod(){
        defaultCartService.getById(TEST_CART_ID);

        verify(cartRepository).findById(TEST_CART_ID);
    }

    @Test
    public void shouldCreateCartSaveNewCart(){
        defaultCartService.createCart();

        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    public void shouldAddToCartEntryCountAsPassedToMethodWhenAddProductCount(){
        BigDecimal addedCount = BigDecimal.ONE;
        defaultCartService.addCurrentCartProductCount(TEST_PRODUCT_ID, addedCount);
        verify(cartEntry).setCount(countCaptor.capture());
        BigDecimal expected = TEST_CART_ENTRY_COUNT.add(addedCount);

        assertEquals(expected, countCaptor.getValue());
    }

    @Test
    public void shouldCreateNewCartEntryIfNotPresentWhenAddProductCount(){
        when(cartEntryStream.findFirst()).thenReturn(Optional.empty());
        BigDecimal count = BigDecimal.ONE;
        CartEntry cartEntry = defaultCartService.addCurrentCartProductCount(TEST_PRODUCT_ID, count);

        assertThat(this.cartEntry, not(cartEntry));
        assertEquals(count, cartEntry.getCount());
    }

    @Test
    public void shouldSerToCartEntryCountAsPassedToMethodWhenAddProductCount(){
        BigDecimal addedCount = BigDecimal.ONE;
        defaultCartService.setCurrentCartProductCount(TEST_PRODUCT_ID, addedCount);
        verify(cartEntry).setCount(countCaptor.capture());

        assertEquals(addedCount, countCaptor.getValue());
    }

    @Test
    public void shouldCreateNewCartEntryIfNotPresentWhenSetProductCount(){
        when(cartEntryStream.findFirst()).thenReturn(Optional.empty());
        BigDecimal count = BigDecimal.ONE;
        CartEntry cartEntry = defaultCartService.setCurrentCartProductCount(TEST_PRODUCT_ID, count);

        assertThat(this.cartEntry, not(cartEntry));
        assertEquals(count, cartEntry.getCount());
    }

    @Test
    public void shouldDeleteProductCallSameCartEntryRepositoryMEthod(){
        defaultCartService.deleteProduct(TEST_PRODUCT_ID);

        verify(cartEntryRepository).delete(cartEntry);
    }
}
