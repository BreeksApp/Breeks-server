package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.BreeksLine;
import project.exception.NotAddedToDatabase;
import project.service.BreeksLineService;

import java.util.List;

@RestController
@RequestMapping("/breeks")
public class BreeksLineController {

    @Autowired
    private BreeksLineService breeksLineService;

    @PostMapping("/addLine")
    public ResponseEntity<?> addLine(@RequestBody BreeksLine line) {
        try {
            breeksLineService.addLine(line);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteLine/{id}")
    public ResponseEntity<?> deleteLine(@PathVariable(name = "id") int id) {
        final boolean deleted = breeksLineService.deleteLine(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editLine/{id}")
    public ResponseEntity<?> editLine(@PathVariable(name = "id") int id, @RequestBody BreeksLine line) {
        final boolean updated = breeksLineService.editLine(id, line);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/listOfLines")
    public ResponseEntity<List<BreeksLine>> getAllLines() {
        List<BreeksLine> list = breeksLineService.listOfLines();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
