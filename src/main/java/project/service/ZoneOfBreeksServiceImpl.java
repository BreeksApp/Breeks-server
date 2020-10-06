package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.ZoneOfBreeks;
import project.exception.NotAddedToDatabase;
import project.repository.ZoneOfBreeksRepository;

import java.util.List;

@Service
public class ZoneOfBreeksServiceImpl implements ZoneOfBreeksService {

    @Autowired
    private ZoneOfBreeksRepository zoneOfBreeksRepository;

    @Override
    public void addZone(ZoneOfBreeks zone) throws NotAddedToDatabase {
        zoneOfBreeksRepository.save(zone);
    }

    @Override
    public boolean deleteZone(Integer id) {
        if (zoneOfBreeksRepository.existsById(id)) {
            zoneOfBreeksRepository.delete(zoneOfBreeksRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    @Override
    public boolean editZone(Integer id, ZoneOfBreeks newZone) throws NotAddedToDatabase {
        if (zoneOfBreeksRepository.existsById(id)) {
            newZone.setId(id);
            zoneOfBreeksRepository.save(newZone);
            return true;
        }
        else return false;
    }

    @Override
    public boolean moveBreek() {
        return false;
    }

    @Override
    public List<ZoneOfBreeks> listOfZones() {
        return (List<ZoneOfBreeks>)zoneOfBreeksRepository.findAll();
    }
}
