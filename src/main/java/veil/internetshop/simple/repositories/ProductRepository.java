package veil.internetshop.simple.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import veil.internetshop.simple.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{

    @Procedure("product_warehouse_status_update_cron_job")
    void updateProductWarehouseStatus();

    List<Product> findById(List<Long> id);

    Optional<Product> findById(Long id);

    Page<Product> findAll(Specification<Product> spec, Pageable pageable);
}
