package project.service;

import project.entity.BreeksLine;
import project.entity.User;

import java.sql.Date;
import java.util.List;

public interface BreeksLineService {
    void addLine(BreeksLine line);
    boolean deleteLine(Integer id, int userId);
    boolean editLine(Integer id, BreeksLine newLine, User userId);
    List<BreeksLine> listOfLines();
    List<BreeksLine> listOfLinesInWeek(Date date, User user);
    BreeksLine findBreeksLine(Date date, String description, User user);
}
