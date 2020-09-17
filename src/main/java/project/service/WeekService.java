package project.service;

import project.entity.Week;
import java.util.List;

public interface WeekService {
    void addWeek(Week week);
    boolean deleteWeek(Integer id);
    boolean editWeek(Integer id, Week newWeek);
    List<Week> listOfWeeks();
    Week findWeek(Integer id);
}
