package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Breek;
import project.exception.NotAddedToDatabase;
import project.repository.BreekRepository;

import java.util.List;

@Service
public class BreekServiceImpl implements BreekService{

    @Autowired
    private BreekRepository breekRepository;

    @Override
    public void addBreek(Breek breek) throws NotAddedToDatabase {
        breekRepository.save(breek);
    }

    @Override
    public boolean deleteBreek(Integer id) {
        if (breekRepository.existsById(id)) {
            breekRepository.delete(breekRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    @Override
    public boolean editBreek(Integer id, Breek newBreek) throws NotAddedToDatabase {
        if (breekRepository.existsById(id)) {
            newBreek.setId(id);
            breekRepository.save(newBreek);
            return true;
        }
        else return false;
    }

    @Override
    public boolean moveBreek() {
        return false;
    }

    @Override
    public List<Breek> listOfBreeks() {
        return (List<Breek>)breekRepository.findAll();
    }
}
