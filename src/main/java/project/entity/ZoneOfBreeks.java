package project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ZoneOfBreeks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id_;

    @Column(nullable = false)
    private String description_;

    @OneToMany
    private List<ZoneOfBreeksCondition> conditions_;

    @OneToMany
    private List<Breek> breeks_;

    public ZoneOfBreeks() {

    }

    public ZoneOfBreeks(Integer id, String description, List<ZoneOfBreeksCondition> conditions,
                        List<Breek> breeks) {
        this.id_ = id;
        this.description_ = description;
        this.conditions_ = conditions;
        this.breeks_ = breeks;
    }

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id;
    }

    public String getDescription() {
        return description_;
    }

    public void setDescription(String description) {
        this.description_ = description;
    }

    public List<ZoneOfBreeksCondition> getConditions() {
        return conditions_;
    }

    public void setConditions(List<ZoneOfBreeksCondition> conditions) {
        this.conditions_ = conditions;
    }

    public List<Breek> getBreeks() {
        return breeks_;
    }

    public void setBreeks(List<Breek> breeks) {
        this.breeks_ = breeks;
    }
}
