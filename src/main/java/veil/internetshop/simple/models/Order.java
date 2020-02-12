package veil.internetshop.simple.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import veil.internetshop.simple.view.IdentificationView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable{

    public static final String FETCH_ALL = "Order[all]";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(IdentificationView.class)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OrderEntry> cartEntries;

    private Timestamp date;
}
