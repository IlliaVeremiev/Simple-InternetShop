package veil.internetshop.simple.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import veil.internetshop.simple.models.enums.WarehouseEntryStatus;
import veil.internetshop.simple.view.IdentificationView;
import veil.internetshop.simple.view.jsonviews.BaseCartView;
import veil.internetshop.simple.view.jsonviews.BaseProductView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "warehouse_entries")
public class WarehouseEntry implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(IdentificationView.class)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    @JsonView(BaseCartView.class)
    private Warehouse warehouse;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonView(BaseProductView.class)
    private BigDecimal count;

    @Column(columnDefinition = "enum")
    @Enumerated(EnumType.STRING)
    @JsonView(BaseProductView.class)
    private WarehouseEntryStatus status;
}
