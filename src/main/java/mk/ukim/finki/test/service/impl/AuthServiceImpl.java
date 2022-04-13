package mk.ukim.finki.test.service.impl;

import mk.ukim.finki.test.model.User;
import mk.ukim.finki.test.model.exceptions.InvalidArguments;
import mk.ukim.finki.test.model.exceptions.InvalidUserCredentials;
import mk.ukim.finki.test.repository.UserRepository;
import mk.ukim.finki.test.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() ||
           password == null || username.isEmpty()) {
            throw new InvalidArguments();
        }
        return userRepository
                .findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentials::new);
    }
}
