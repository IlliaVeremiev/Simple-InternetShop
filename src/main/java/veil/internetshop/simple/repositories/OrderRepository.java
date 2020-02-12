package veil.internetshop.simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import veil.internetshop.simple.models.Order;
import veil.internetshop.simple.models.User;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>{

	Optional<Order> findByUser(User user);
}