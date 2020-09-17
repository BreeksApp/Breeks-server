package project.service;

import project.entity.Image;

public interface ImageService {
    boolean addImage(Image image);
    boolean deleteImage(Integer id);
    boolean editImage(Integer id, Image newImage);
    Image findImage(Integer id);
}
