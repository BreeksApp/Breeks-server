package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.entity.Image;
import project.exception.NotAddedToDatabase;
import project.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/addImage")
    public ResponseEntity<?> addImage(@RequestBody Image image) {
        try {
            imageService.addImage(image);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteImage/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") int id) {
        boolean deleted = imageService.deleteImage(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editImage/{id}")
    public ResponseEntity<?> editImage(@PathVariable("id") int id, @RequestBody Image image) {
        boolean updated = imageService.editImage(id, image);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/getImage/{id}")
    public ResponseEntity<Image> getImage(@PathVariable("id") int id) {
        Image image = imageService.findImage(id);
        if (image != null) return new ResponseEntity<>(image, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
