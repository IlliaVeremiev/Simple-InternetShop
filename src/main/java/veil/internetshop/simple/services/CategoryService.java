package veil.internetshop.simple.services;

import veil.internetshop.simple.data.CategoryProductCount;
import veil.internetshop.simple.models.Category;

import java.util.List;

public interface CategoryService{

	List<CategoryProductCount> findAllAndProductsCount();

	Category findByCode(String code);

	List<Category> getAll();
}
