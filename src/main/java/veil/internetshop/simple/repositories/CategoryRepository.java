package veil.internetshop.simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import veil.internetshop.simple.data.CategoryProductCount;
import veil.internetshop.simple.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Optional<Category> findByCode(final String code);

	List<Category> findAll();

	@Query("SELECT NEW veil.internetshop.simple.data.CategoryProductCount(cte, count(p)) FROM Product p LEFT JOIN p.category cte JOIN p.catalog cta WHERE cta.active = true GROUP BY cte.id")
	List<CategoryProductCount> findAllAndProductsCount();
}