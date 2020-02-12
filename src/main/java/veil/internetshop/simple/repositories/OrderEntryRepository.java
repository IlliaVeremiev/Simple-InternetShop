package veil.internetshop.simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import veil.internetshop.simple.models.Order;
import veil.internetshop.simple.models.OrderEntry;
import veil.internetshop.simple.models.Product;

import java.util.List;
import java.util.Optional;

public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long>{

	List<OrderEntry> findByOrder(Order order);

	Optional<OrderEntry> findByOrderAndProduct(Order order, Product product);
}