package veil.internetshop.simple.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import veil.internetshop.simple.data.SearchData;
import veil.internetshop.simple.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService{

	Product getProduct(Long id);

	Page<Product> getProductsPage(Pageable pageable, SearchData searchDTO);

	List<Product> getProducts(List<Long> ids);

	BigDecimal calculateProductPrice(Product product, BigDecimal quantity);
}
