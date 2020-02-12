package veil.internetshop.simple.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import veil.internetshop.simple.data.SearchData;
import veil.internetshop.simple.models.Product;
import veil.internetshop.simple.repositories.specifications.specifications.SpecificationChain;
import veil.internetshop.simple.services.CategoryService;
import veil.internetshop.simple.services.SearchService;

public class DefaultSearchService implements SearchService{

    @Autowired
    private CategoryService categoryService;

    @Override
    public SpecificationChain<Product> createProductSpecification(SearchData searchDTO){
        SpecificationChain<Product> complexProductSpecification = new SpecificationChain<>();

        complexProductSpecification.add((r, q, c) -> c.isTrue(r.get("catalog").get("active")));

        if(searchDTO.getMinPrice() != null)
            complexProductSpecification.add((r, q, c) -> c.greaterThanOrEqualTo(r.get("price"), searchDTO.getMinPrice()));

        if(searchDTO.getMaxPrice() != null)
            complexProductSpecification.add((r, q, c) -> c.lessThanOrEqualTo(r.get("price"), searchDTO.getMaxPrice()));

        if(StringUtils.isNotEmpty(searchDTO.getSearchText()))
            complexProductSpecification.add((r, q, c) -> c.like(r.get("name"), "%" + searchDTO.getSearchText() + "%"));

        if(StringUtils.isNotEmpty(searchDTO.getCategory())){
            complexProductSpecification.add((r, q, c) -> c.equal(r.get("category").get("code"), searchDTO.getCategory()));
        }

        return complexProductSpecification;
    }
}
