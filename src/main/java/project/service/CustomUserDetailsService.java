package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.repository.UserRepository;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(username).
                orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    public User loadUserById(int id) throws UsernameNotFoundException {
        return userRepository.findUserById(id).
                orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    public void createUser(User user) throws NotAddedToDatabase {
        userRepository.save(user);
    }

    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.delete(userRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    public boolean editUser(Integer id, User user) throws NotAddedToDatabase {
        if (userRepository.existsById(id)) {
            user.setId(id);
            userRepository.save(user);
            return true;
        }
        else return false;
    }

    public List<User> listOfUsers() {
        return userRepository.findAll();
    }
}
