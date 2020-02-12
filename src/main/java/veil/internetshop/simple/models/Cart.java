package veil.internetshop.simple.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import veil.internetshop.simple.view.IdentificationView;
import veil.internetshop.simple.view.jsonviews.BaseCartView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "carts")
@ToString(exclude = "user")
public class Cart implements Serializable{

    public static final String FETCH_ALL = "Cart[all]";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(IdentificationView.class)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "cart")
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonView(BaseCartView.class)
    private List<CartEntry> cartEntries;
}
