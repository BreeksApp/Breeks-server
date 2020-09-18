package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.Week;
import project.exception.NotAddedToDatabase;
import project.service.WeekService;

import java.util.List;

@RestController
@RequestMapping("/week")
public class WeekController {

    @Autowired
    private WeekService weekService;

    @PostMapping("/addWeek")
    public ResponseEntity<?> addWeek(@RequestBody Week week) {
        try {
            weekService.addWeek(week);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteWeek/{id}")
    public ResponseEntity<?> deleteWeek(@PathVariable("id") int id) {
        boolean deleted = weekService.deleteWeek(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editWeek/{id}")
    public ResponseEntity<?> editWeek(@PathVariable("id") int id, @RequestBody Week week) {
        boolean updated = weekService.editWeek(id, week);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/listOfWeeks")
    public ResponseEntity<List<Week>> getAllWeeks() {
        List<Week> list = weekService.listOfWeeks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getWeek/{id}")
    public ResponseEntity<Week> getWeek(@PathVariable("id") int id) {
        Week week = weekService.findWeek(id);
        if (week != null) return new ResponseEntity<>(week, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
