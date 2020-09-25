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

    @Column(nullable = false)
    private String linkToEmoji;

    @OneToMany
    private List<ZoneOfBreeksStates> states;

    public ZoneOfBreeks() {

    }

    public ZoneOfBreeks(Integer id, String description, List<ZoneOfBreeksCondition> conditions,
                        String linkToEmoji, List<ZoneOfBreeksStates> states) {
        this.id = id;
        this.description = description;
        this.conditions = conditions;
        this.linkToEmoji = linkToEmoji;
        this.states = states;
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

    public String getLinkToEmoji() {
        return linkToEmoji;
    }

    public void setLinkToEmoji(String linkToEmoji) {
        this.linkToEmoji = linkToEmoji;
    }

    public List<ZoneOfBreeksStates> getStates() {
        return states;
    }

    public void setStates(List<ZoneOfBreeksStates> states) {
        this.states = states;
    }
}
