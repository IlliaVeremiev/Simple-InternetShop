package veil.internetshop.simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import veil.internetshop.simple.models.Cart;
import veil.internetshop.simple.models.User;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>{

	Optional<Cart> findByUser(User user);

	Optional<Cart> findById(Long id);
}