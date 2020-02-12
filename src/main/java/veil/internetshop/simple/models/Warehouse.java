package veil.internetshop.simple.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import veil.internetshop.simple.view.IdentificationView;
import veil.internetshop.simple.view.jsonviews.BaseCartView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "warehouses")
public class Warehouse implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(IdentificationView.class)
    private Long id;

    @JsonView(BaseCartView.class)
    private String name;

    @OneToMany(mappedBy = "warehouse")
    private List<WarehouseEntry> warehouseEntries;
}
