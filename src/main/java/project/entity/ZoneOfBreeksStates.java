package project.entity;

import javax.persistence.*;

@Entity
public class ZoneOfBreeksStates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(nullable = false)
    private Integer color;

    public ZoneOfBreeksStates() {

    }

    public ZoneOfBreeksStates(Integer id, Integer color) {
        this.id = id;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}
