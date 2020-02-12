package veil.internetshop.simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import veil.internetshop.simple.models.Catalog;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Catalog, Long>{

	List<Catalog> findByActiveTrue();
}
