package veil.internetshop.simple.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import veil.internetshop.simple.data.SearchData;
import veil.internetshop.simple.exceptions.service.ResourceNotFoundException;
import veil.internetshop.simple.models.Product;
import veil.internetshop.simple.repositories.ProductRepository;
import veil.internetshop.simple.services.ProductService;
import veil.internetshop.simple.services.SearchService;

import java.math.BigDecimal;
import java.util.List;

public class DefaultProductService implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SearchService searchService;

    @Override
    public Product getProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
    }

    @Override
    public List<Product> getProducts(List<Long> ids){
        if(CollectionUtils.isEmpty(ids)){
            throw new ResourceNotFoundException("No one id selected");
        }
        List<Product> products = productRepository.findById(ids);
        if(products.size() != ids.size()){
            throw new ResourceNotFoundException("Unable to find all products");
        }
        return products;
    }

    @Override
    public BigDecimal calculateProductPrice(Product product, BigDecimal quantity){
        BigDecimal price = product.getPrice();
        BigDecimal productQuantity = product.getQuantity();
        return quantity.divide(productQuantity, BigDecimal.ROUND_UP).multiply(price);
    }

    @Override
    public Page<Product> getProductsPage(Pageable pageable, SearchData searchDTO){
        Pageable availablePageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), new Sort(Sort.Direction.DESC, "warehouseAvailable").and(pageable.getSort()));
        return productRepository.findAll(searchService.createProductSpecification(searchDTO), availablePageable);
    }
}
