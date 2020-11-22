package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Image;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.repository.ImageRepository;

import java.sql.Date;

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
    public boolean editImage(Date date, User user, Image newImage) {
        if (imageRepository.existsByDateAndUser(date, user)) {
            newImage.setId(
                    imageRepository.findByDateAndUser(date, user).get().getId()
            );
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

    @Override
    public Image findImage(Date date, User user) {
        if (imageRepository.existsByDateAndUser(date, user)) {
            return imageRepository.findByDateAndUser(date, user).get();
        }
        return null;
    }
}
