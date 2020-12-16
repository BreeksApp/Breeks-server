package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.repository.UserRepository;
import project.service.mail.MailProperties;
import project.service.mail.MailSender;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

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
        UserDetails userFromDB = null;
        try {
            userFromDB = loadUserByUsername(user.getUsername());
            throw new SecurityException("This user already exists. Please choose another username!");
        }
        catch (UsernameNotFoundException e) {
            user.setRoles(Collections.singletonList("ROLE_USER"));
            user.setActivationCode(UUID.randomUUID().toString());

            userRepository.save(user);

            if (!StringUtils.isEmpty(user.getUsername())) {
                String message = String.format(
                        mailProperties.getDefaultMessage(), user.getUsername(),
                        user.getActivationCode()
                );
                mailSender.send(user.getUsername(), "Activation code", message);
            }
        }
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

    public boolean activateUser(String code) {
        User user = userRepository.findUserByActivationCode(code);
        if (user != null) {
            user.setActivationCode(null);
            userRepository.save(user);
            return true;
        }
        deleteUser(user.getId());
        return false;
    }
}
