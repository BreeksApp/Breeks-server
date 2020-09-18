package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.Breek;
import project.exception.NotAddedToDatabase;
import project.service.BreekService;

import java.util.List;

@RestController
@RequestMapping("/breek")
public class BreekController {

    @Autowired
    private BreekService breekService;

    @PostMapping("/addBreek")
    public ResponseEntity<?> addBreek(@RequestBody Breek breek) {
        try {
            breekService.addBreek(breek);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteBreek/{id}")
    public ResponseEntity<?> deleteBreek(@PathVariable(name = "id") int id) {
        final boolean deleted = breekService.deleteBreek(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editBreek/{id}")
    public ResponseEntity<?> editBreek(@PathVariable(name = "id") int id, @RequestBody Breek breek) {
        final boolean updated = breekService.editBreek(id, breek);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/listOfBreeks")
    public ResponseEntity<List<Breek>> getAllBreeks() {
        List<Breek> list = breekService.listOfBreeks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
