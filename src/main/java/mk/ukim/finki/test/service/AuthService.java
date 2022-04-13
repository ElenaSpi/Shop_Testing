package mk.ukim.finki.test.service;

import mk.ukim.finki.test.model.User;

public interface AuthService {
    User login(String username, String password);
}
