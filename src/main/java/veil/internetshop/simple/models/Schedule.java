package veil.internetshop.simple.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "Schedules")
@NamedEntityGraph(name = Schedule.FETCH_ALL,
        attributeNodes = {
                @NamedAttributeNode("cronJobs")
        }
)
public class Schedule{

    public static final String FETCH_ALL = "Schedule[all]";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String expression;

    private String name;

    private String description;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    private List<CronJob> cronJobs;
}
