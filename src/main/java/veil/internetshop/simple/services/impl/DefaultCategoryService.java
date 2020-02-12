package veil.internetshop.simple.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import veil.internetshop.simple.data.CategoryProductCount;
import veil.internetshop.simple.exceptions.service.ResourceNotFoundException;
import veil.internetshop.simple.models.Category;
import veil.internetshop.simple.repositories.CategoryRepository;
import veil.internetshop.simple.services.CategoryService;

import java.util.List;

public class DefaultCategoryService implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryProductCount> findAllAndProductsCount(){
		return categoryRepository.findAllAndProductsCount();
	}

	@Override
	public Category findByCode(String code){
		return categoryRepository.findByCode(code)
				.orElseThrow(() -> new ResourceNotFoundException("exception.no.such.category"));
	}

	@Override
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
}
