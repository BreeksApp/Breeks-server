package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Week;
import project.exception.NotAddedToDatabase;
import project.repository.WeekRepository;

import java.util.List;

@Service
public class WeekServiceImpl implements WeekService {

    @Autowired
    private WeekRepository weekRepository;

    @Override
    public void addWeek(Week week) throws NotAddedToDatabase {
        weekRepository.save(week);
    }

    @Override
    public boolean deleteWeek(Integer id) {
        if (weekRepository.existsById(id)) {
            weekRepository.delete(weekRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    @Override
    public boolean editWeek(Integer id, Week newWeek) throws NotAddedToDatabase {
        if (weekRepository.existsById(id)) {
            newWeek.setId(id);
            weekRepository.save(newWeek);
            return true;
        }
        else return false;
    }

    @Override
    public List<Week> listOfWeeks() {
        return (List<Week>)weekRepository.findAll();
    }

    @Override
    public Week findWeek(Integer id) {
        if (weekRepository.existsById(id)) {
            return weekRepository.findById(id).get();
        }
        return null;
    }
}
