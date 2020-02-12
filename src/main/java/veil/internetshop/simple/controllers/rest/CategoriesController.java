package veil.internetshop.simple.controllers.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import veil.internetshop.simple.data.CategoryProductCount;
import veil.internetshop.simple.services.CategoryService;
import veil.internetshop.simple.view.jsonviews.BaseCategoryView;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController{

    @Autowired
    private CategoryService categoriesService;

    /**
     * Returns list of all exsiting categories
     *
     * @return list of all exsiting categories
     */
    @GetMapping
    @JsonView(BaseCategoryView.class)
    public List<CategoryProductCount> getCategories(){
        return categoriesService.findAllAndProductsCount();
    }
}
