package project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id_;

    @Column(nullable = false)
    private String name_;

    @OneToMany
    private List<ElementInDay> elementsInDay_;

    public DayOfWeek() {

    }

    public DayOfWeek(Integer id, String name, List<ElementInDay> elementsInDay) {
        this.id_ = id;
        this.name_ = name;
        this.elementsInDay_ = elementsInDay;
    }

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id;
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    public List<ElementInDay> getElementsInDay() {
        return elementsInDay_;
    }

    public void setElementsInDay(List<ElementInDay> elementsInDay) {
        this.elementsInDay_ = elementsInDay;
    }
}
