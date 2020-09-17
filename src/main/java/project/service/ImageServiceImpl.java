package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Image;
import project.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public boolean addImage(Image image) {
        return false;
    }

    @Override
    public boolean deleteImage(Integer id) {
        return false;
    }

    @Override
    public boolean editImage(Integer id, Image newImage) {
        return false;
    }

    @Override
    public Image findImage(Integer id) {
        return null;
    }
}
