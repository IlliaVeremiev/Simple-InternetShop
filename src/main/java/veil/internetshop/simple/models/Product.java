package veil.internetshop.simple.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import veil.internetshop.simple.view.IdentificationView;
import veil.internetshop.simple.view.jsonviews.BaseProductView;
import veil.internetshop.simple.view.serializers.CurrencySerializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Products")
public class Product implements Serializable{

    public static final String FETCH_ALL = "Product[all]";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(IdentificationView.class)
    private Long id;

    @JsonSerialize(using = CurrencySerializer.class)
    @JsonView(BaseProductView.class)
    private BigDecimal price;

    @JsonView(BaseProductView.class)
    private BigDecimal quantity;

    @JsonView(BaseProductView.class)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    @JsonView(BaseProductView.class)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "catalog")
    @JsonView(BaseProductView.class)
    private Catalog catalog;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Set<Media> photos;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "primary_photo")
    @JsonView(BaseProductView.class)
    private Media primaryPhoto;

    @OneToMany(mappedBy = "product")
    @JsonView(BaseProductView.class)
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<WarehouseEntry> warehouseEntries;

    @Column(name = "_warehouse_available_")
    private boolean warehouseAvailable;
}
