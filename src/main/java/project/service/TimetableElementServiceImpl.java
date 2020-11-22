package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.TimetableElement;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.repository.TimetableElementRepository;

import java.sql.Date;
import java.util.List;

@Service
public class TimetableElementServiceImpl implements TimetableElementService {

    @Autowired
    private TimetableElementRepository timetableElementRepository;

    @Override
    public void addElement(TimetableElement element) throws NotAddedToDatabase {
        timetableElementRepository.save(element);
    }

    @Override
    public boolean deleteElement(Integer id) {
        if (timetableElementRepository.existsById(id)) {
            timetableElementRepository.delete(timetableElementRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteElement(Date date, byte number, User user) {
        if (timetableElementRepository.existsByDateAndNumberAndUser(date, number, user)) {
            timetableElementRepository.delete(
                    timetableElementRepository.findByDateAndNumberAndUser(date, number, user).get()
            );
            return true;
        }
        else return false;
    }

    @Override
    public boolean editElement(Integer id, TimetableElement newElement) throws NotAddedToDatabase {
        if (timetableElementRepository.existsById(id)) {
            newElement.setId(id);
            timetableElementRepository.save(newElement);
            return true;
        }
        else return false;
    }

    @Override
    public boolean editElement(Date date, byte number, User user, TimetableElement newElement) {
        if (timetableElementRepository.existsByDateAndNumberAndUser(date, number, user)) {
            newElement.setId(
                    timetableElementRepository.findByDateAndNumberAndUser(date, number, user).get().getId()
            );
            timetableElementRepository.save(newElement);
            return true;
        }
        else return false;
    }

    @Override
    public boolean moveElement() {
        return false;
    }

    @Override
    public List<TimetableElement> listOfElements() {
        return timetableElementRepository.findAll();
    }

    @Override
    public List<TimetableElement> listOfElements(Date date, User user) {
        return timetableElementRepository.findAllByDateAndUser(date, user);
    }
}
