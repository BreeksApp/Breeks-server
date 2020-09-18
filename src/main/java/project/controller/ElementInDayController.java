package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.ElementInDay;
import project.exception.NotAddedToDatabase;
import project.service.ElementInDayService;

import java.util.List;

@RestController
@RequestMapping("/elementInDay")
public class ElementInDayController {

    @Autowired
    private ElementInDayService elementInDayService;

    @PostMapping("/addElementInDay")
    public ResponseEntity<?> addElementInDay(@RequestBody ElementInDay elementInDay) {
        try {
            elementInDayService.addElement(elementInDay);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteElementInDay/{id}")
    public ResponseEntity<?> deleteElementInDay(@PathVariable(name = "id") int id) {
        final boolean deleted = elementInDayService.deleteElement(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editElementInDay/{id}")
    public ResponseEntity<?> editElementInDay(@PathVariable(name = "id") int id,
                                              @RequestBody ElementInDay elementInDay) {
        final boolean updated = elementInDayService.editElement(id, elementInDay);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/listOfElementsInDay")
    public ResponseEntity<List<ElementInDay>> getAllElementsInDay() {
        List<ElementInDay> list = elementInDayService.listOfElements();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
