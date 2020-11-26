package project.service;

import project.entity.BreeksLine;
import project.entity.User;

import java.sql.Date;
import java.util.List;

public interface BreeksLineService {
    void addLine(BreeksLine line);
    boolean deleteLine(Integer id);
    boolean deleteLine(Date date, String description, User user);
    boolean editLine(Integer id, BreeksLine newLine);
    boolean editLine(Date date, String description, User user, BreeksLine newLine);
    boolean moveBreek(); // ?
    List<BreeksLine> listOfLines();
    List<BreeksLine> listOfLinesInWeek(Date date, User user);
    BreeksLine findBreeksLine(Date date, String description, User user);
}
