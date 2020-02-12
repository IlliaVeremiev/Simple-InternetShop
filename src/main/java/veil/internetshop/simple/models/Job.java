package veil.internetshop.simple.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Jobs")
public class Job{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String beanId;

    private String name;

    private String description;
}
