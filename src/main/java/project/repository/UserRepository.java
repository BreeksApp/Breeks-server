package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUserName(String username);
    Optional<User> findUserById(int id);
    User findUserByActivationCode(String code);
    boolean existsUserByUserName(String username);
}
