// DAO
package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.Image;
import project.entity.User;

import java.sql.Date;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByDateAndUser(Date date, User user);
    boolean existsByDateAndUser(Date date, User user);
}
