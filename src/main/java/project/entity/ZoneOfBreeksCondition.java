package project.entity;

import javax.persistence.*;

@Entity
public class ZoneOfBreeksCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id_;

    @Column(nullable = false)
    private Boolean state_;

    public ZoneOfBreeksCondition() {

    }

    public ZoneOfBreeksCondition(Integer id, Boolean state) {
        this.id_ = id;
        this.state_ = state;
    }

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id;
    }

    public Boolean getState() {
        return state_;
    }

    public void setState(Boolean state) {
        this.state_ = state;
    }
}
