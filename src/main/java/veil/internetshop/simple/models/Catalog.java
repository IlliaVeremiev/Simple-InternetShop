package veil.internetshop.simple.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import veil.internetshop.simple.view.IdentificationView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Catalogs")
public class Catalog implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(IdentificationView.class)
	private long id;

	private String name;

	private boolean active;
}
