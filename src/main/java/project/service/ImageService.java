package project.service;

import project.entity.Image;
import project.entity.User;

import java.sql.Date;

public interface ImageService {
    void addImage(Image image);
    boolean deleteImage(Integer id);
    boolean editImage(Integer id, Image newImage);
    boolean editImage(Date date, User user, Image newImage);
    Image findImage(Integer id);
    Image findImage(Date date, User user);
}
