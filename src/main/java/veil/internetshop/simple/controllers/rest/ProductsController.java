package veil.internetshop.simple.controllers.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import veil.internetshop.simple.data.SearchData;
import veil.internetshop.simple.models.Product;
import veil.internetshop.simple.services.ProductService;
import veil.internetshop.simple.view.jsonviews.BaseProductView;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController{

	@Autowired
	private ProductService productService;

	/**
	 * Returns sorted, filtered and splitted by pages products set, filtered by catalog validity and ordered by warehouse stock validity
	 *
	 * @param pageable - pageable
	 * @return sorted, filtered and splitted by pages products set
	 */
	@GetMapping
	@JsonView(BaseProductView.class)
	public Page<Product> sorted(@PageableDefault(sort = {"id"}, size = 24, direction = Sort.Direction.ASC) Pageable pageable,
								SearchData searchDTO){
		return productService.getProductsPage(pageable, searchDTO);
	}

	@GetMapping("/list/{ids}")
	@JsonView(BaseProductView.class)
	public List<Product> findByIds(@PathVariable(value = "ids") List<Long> ids){
		return productService.getProducts(ids);
	}

	@GetMapping("/{id}")
	@JsonView(BaseProductView.class)
	public Product findById(@PathVariable("id") Long id){
		return productService.getProduct(id);
	}
}
