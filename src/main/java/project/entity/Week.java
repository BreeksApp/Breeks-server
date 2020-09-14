package project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id_;

    @OneToMany
    private List<DayOfWeek> daysOfWeek_;

    @OneToMany
    private List<ZoneOfBreeks> zonesOfWeek_;

    @OneToMany
    private List<Note> notesOfWeek_;

    @OneToOne
    private Image imageOfWeek_;

    public Week() {

    }

    public Week(Integer id, List<DayOfWeek> daysOfWeek, List<ZoneOfBreeks> zonesOfWeek,
                List<Note> notesOfWeek, Image imageOfWeek) {
        this.id_ = id;
        this.daysOfWeek_ = daysOfWeek;
        this.zonesOfWeek_ = zonesOfWeek;
        this.notesOfWeek_ = notesOfWeek;
        this.imageOfWeek_ = imageOfWeek;
    }

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id;
    }

    public List<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek_;
    }

    public void setDaysOfWeek(List<DayOfWeek> daysOfWeek) {
        this.daysOfWeek_ = daysOfWeek;
    }

    public List<ZoneOfBreeks> getZonesOfWeek() {
        return zonesOfWeek_;
    }

    public void setZonesOfWeek(List<ZoneOfBreeks> zonesOfWeek) {
        this.zonesOfWeek_ = zonesOfWeek;
    }

    public List<Note> getNotesOfWeek() {
        return notesOfWeek_;
    }

    public void setNotesOfWeek(List<Note> notesOfWeek) {
        this.notesOfWeek_ = notesOfWeek;
    }

    public Image getImageOfWeek() {
        return imageOfWeek_;
    }

    public void setImageOfWeek(Image imageOfWeek) {
        this.imageOfWeek_ = imageOfWeek;
    }
}
