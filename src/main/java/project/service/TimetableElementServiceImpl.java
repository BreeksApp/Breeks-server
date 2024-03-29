package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.TimetableElement;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.repository.TimetableElementRepository;

import java.sql.Date;
import java.sql.Time;
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
    public boolean deleteElement(Integer id, User user) {
        if (timetableElementRepository.existsByElementIdAndUser(id, user)) {
            TimetableElement element = timetableElementRepository.findByElementIdAndUser(id, user).get();
            if (element.getUser().getId() == user.getId()) {
                timetableElementRepository.delete(
                        timetableElementRepository.findByElementIdAndUser(id, user).get()
                );
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editElement(Integer id, User user, TimetableElement newElement) {
        if (timetableElementRepository.existsByElementIdAndUser(id, user)) {
            TimetableElement element = timetableElementRepository.findByElementIdAndUser(id, user).get();
            if (element.getUser().getId() == user.getId()) {
                newElement.setElementId(
                        timetableElementRepository.findByElementIdAndUser(id, user).get().getElementId()
                );
                newElement.setUser(user);
                timetableElementRepository.save(newElement);
                return true;
            }
        }
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
