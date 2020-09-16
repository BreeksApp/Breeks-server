package project.entity;

import javax.persistence.*;

@Entity
public class ZoneOfBreeksCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(nullable = false)
    private Boolean state;

    public ZoneOfBreeksCondition() {

    }

    public ZoneOfBreeksCondition(Integer id, Boolean state) {
        this.id = id;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
