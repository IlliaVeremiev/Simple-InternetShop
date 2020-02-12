package veil.internetshop.simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import veil.internetshop.simple.models.Cart;
import veil.internetshop.simple.models.CartEntry;
import veil.internetshop.simple.models.Product;

import java.util.List;
import java.util.Optional;

public interface CartEntryRepository extends JpaRepository<CartEntry, Long>{

    List<CartEntry> findByCart(Cart cart);

    Optional<CartEntry> findByCartAndProduct(Cart cart, Product product);
}