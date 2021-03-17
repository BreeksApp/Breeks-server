package project.service;

import project.entity.SessionKey;
import project.entity.User;

public interface SessionKeyService {
    boolean existsByKey(String key);
    SessionKey generateKey(User user);
    boolean deleteKey(String key);
    SessionKey findByKey(String key);
}
