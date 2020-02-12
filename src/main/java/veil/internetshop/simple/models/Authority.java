package veil.internetshop.simple.models;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import veil.internetshop.simple.view.IdentificationView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonView(IdentificationView.class)
    private String value;

    @Override
    public String getAuthority(){
        return value;
    }
}
