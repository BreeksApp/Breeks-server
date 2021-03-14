package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.SessionKey;
import project.entity.User;
import project.repository.SessionKeyRepository;

import java.util.Random;
import java.util.UUID;

@Service
public class SessionKeyServiceImpl implements SessionKeyService {

    @Autowired
    private SessionKeyRepository sessionKeyRepository;

    @Override
    public boolean existsByKey(String key) {
        return sessionKeyRepository.existsById(key);
    }

    @Override
    public SessionKey generateKey(User user) {
        return sessionKeyRepository.save(
                new SessionKey(UUID.randomUUID().toString(), user)
        );
    }

    @Override
    public boolean deleteKey(String key) {
        if (existsByKey(key)) {
            sessionKeyRepository.deleteById(key);
            return true;
        }
        return false;
    }
}
