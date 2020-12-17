package project.service;

import project.entity.TimetableElement;
import project.entity.User;

import java.sql.Date;
import java.util.List;

public interface TimetableElementService {
    void addElement(TimetableElement element);
    boolean deleteElement(Integer id, User user);
    boolean editElement(Integer id, User user, TimetableElement newElement);
    List<TimetableElement> listOfElements();
    List<TimetableElement> listOfElements(Date date, User user);
}
