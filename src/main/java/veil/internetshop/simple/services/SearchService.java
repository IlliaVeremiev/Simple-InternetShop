package veil.internetshop.simple.services;

import veil.internetshop.simple.data.SearchData;
import veil.internetshop.simple.models.Product;
import veil.internetshop.simple.repositories.specifications.specifications.SpecificationChain;

public interface SearchService{

	SpecificationChain<Product> createProductSpecification(SearchData searchDTO);
}
