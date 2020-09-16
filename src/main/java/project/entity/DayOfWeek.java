package project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<ElementInDay> elementsInDay;

    public DayOfWeek() {

    }

    public DayOfWeek(Integer id, String name, List<ElementInDay> elementsInDay) {
        this.id = id;
        this.name = name;
        this.elementsInDay = elementsInDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ElementInDay> getElementsInDay() {
        return elementsInDay;
    }

    public void setElementsInDay(List<ElementInDay> elementsInDay) {
        this.elementsInDay = elementsInDay;
    }
}
