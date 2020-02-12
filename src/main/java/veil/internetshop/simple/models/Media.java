package veil.internetshop.simple.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import veil.internetshop.simple.view.IdentificationView;
import veil.internetshop.simple.view.jsonviews.BaseProductView;
import veil.internetshop.simple.view.serializers.ProductImagePathSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Medias")
public class Media implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(IdentificationView.class)
    private Long id;

    @JsonView(BaseProductView.class)
    @JsonSerialize(using = ProductImagePathSerializer.class)
    private String path;

    private Long owner;

    private String type;

    private Timestamp uploadDate;
}
