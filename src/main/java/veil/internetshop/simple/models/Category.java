package veil.internetshop.simple.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import veil.internetshop.simple.view.IdentificationView;
import veil.internetshop.simple.view.jsonviews.BaseCategoryView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Categories")
public class Category implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(IdentificationView.class)
    private Long id;

    @Column(unique = true)
    @JsonView(BaseCategoryView.class)
    private String code;

    @JsonView(BaseCategoryView.class)
    private String name;
}
