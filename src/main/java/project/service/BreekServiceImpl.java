package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Breek;
import project.repository.BreekRepository;

import java.util.List;

@Service
public class BreekServiceImpl implements BreekService{

    @Autowired
    private BreekRepository breekRepository;

    @Override
    public boolean addBreek(Breek breek) {
        return false;
    }

    @Override
    public boolean deleteBreek(Integer id) {
        return false;
    }

    @Override
    public boolean editBreek(Integer id, Breek newBreek) {
        return false;
    }

    @Override
    public boolean moveBreek() {
        return false;
    }

    @Override
    public List<Breek> listOfBreeks() {
        return null;
    }
}
