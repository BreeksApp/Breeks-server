package project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ZoneOfBreeks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String description;

    @OneToMany
    private List<ZoneOfBreeksCondition> conditions;

    @OneToMany
    private List<Breek> breeks;

    public ZoneOfBreeks() {

    }

    public ZoneOfBreeks(Integer id, String description, List<ZoneOfBreeksCondition> conditions,
                        List<Breek> breeks) {
        this.id = id;
        this.description = description;
        this.conditions = conditions;
        this.breeks = breeks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ZoneOfBreeksCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<ZoneOfBreeksCondition> conditions) {
        this.conditions = conditions;
    }

    public List<Breek> getBreeks() {
        return breeks;
    }

    public void setBreeks(List<Breek> breeks) {
        this.breeks = breeks;
    }
}
