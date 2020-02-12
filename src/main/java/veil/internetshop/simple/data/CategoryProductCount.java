package veil.internetshop.simple.data;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import veil.internetshop.simple.models.Category;
import veil.internetshop.simple.view.jsonviews.BaseCategoryView;

@Data
@AllArgsConstructor
public class CategoryProductCount{

    @JsonView(BaseCategoryView.class)
    private Category category;

    @JsonView(BaseCategoryView.class)
    private long count;
}