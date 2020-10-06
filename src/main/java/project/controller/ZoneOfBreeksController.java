package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.ZoneOfBreeks;
import project.exception.NotAddedToDatabase;
import project.service.ZoneOfBreeksService;

import java.util.List;

@RestController
@RequestMapping("/breeks")
public class ZoneOfBreeksController {

    @Autowired
    private ZoneOfBreeksService zoneOfBreeksService;

    @PostMapping("/addZone")
    public ResponseEntity<?> addZone(@RequestBody ZoneOfBreeks zone) {
        try {
            zoneOfBreeksService.addZone(zone);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteZone/{id}")
    public ResponseEntity<?> deleteZone(@PathVariable(name = "id") int id) {
        final boolean deleted = zoneOfBreeksService.deleteZone(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editZone/{id}")
    public ResponseEntity<?> editZone(@PathVariable(name = "id") int id, @RequestBody ZoneOfBreeks zone) {
        final boolean updated = zoneOfBreeksService.editZone(id, zone);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/listOfZones")
    public ResponseEntity<List<ZoneOfBreeks>> getAllZones() {
        List<ZoneOfBreeks> list = zoneOfBreeksService.listOfZones();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
