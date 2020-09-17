package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Week;
import project.repository.WeekRepository;

import java.util.List;

@Service
public class WeekServiceImpl implements WeekService {

    @Autowired
    private WeekRepository weekRepository;

    @Override
    public boolean addWeek(Week week) {
        return false;
    }

    @Override
    public boolean deleteWeek(Integer id) {
        return false;
    }

    @Override
    public boolean editWeek(Integer id, Week newWeek) {
        return false;
    }

    @Override
    public List<Week> listOfWeeks() {
        return null;
    }

    @Override
    public Week findWeek(Integer id) {
        return null;
    }
}
