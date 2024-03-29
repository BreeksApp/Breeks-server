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
        Image imageFromDB = findImage(image.getDate(), image.getUser());
        if (imageFromDB != null) {
            image.setId(imageFromDB.getId());
            imageFromDB.setLinkToImage(image.getLinkToImage());
        }
        imageRepository.save(image);
    }

    @Override
    public boolean deleteImage(Integer id, int userId) {
        if (imageRepository.existsById(id)) {
            Image image = imageRepository.findById(id).get();
            if (image.getUser().getId() == userId) {
                imageRepository.delete(imageRepository.findById(id).get());
                return true;
            }
        }
        return false;
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
            newImage.setUser(user);
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
