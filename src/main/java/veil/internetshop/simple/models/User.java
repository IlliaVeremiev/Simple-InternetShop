package veil.internetshop.simple.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import veil.internetshop.simple.view.IdentificationView;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable{

    public static final String FETCH_ALL = "User[all]";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonView(IdentificationView.class)
    private String email;

    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    @JsonView(IdentificationView.class)
    private List<Authority> authorities;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> orders;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    private boolean locked;

    private boolean enabled;
}
