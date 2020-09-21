package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Image;
import project.exception.NotAddedToDatabase;
import project.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void addImage(Image image) throws NotAddedToDatabase {
        imageRepository.save(image);
    }

    @Override
    public boolean deleteImage(Integer id) {
        if (imageRepository.existsById(id)) {
            imageRepository.delete(imageRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    @Override
    public boolean editImage(Integer id, Image newImage) throws NotAddedToDatabase {
        if (imageRepository.existsById(id)) {
            newImage.setId(id);
            imageRepository.save(newImage);
            return true;
        }
        else return false;
    }

    @Override
    public Image findImage(Integer id) {
        if (imageRepository.existsById(id)) {
            return imageRepository.findById(id).get();
        }
        return null;
    }
}
