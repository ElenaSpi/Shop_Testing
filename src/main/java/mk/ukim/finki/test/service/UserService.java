package mk.ukim.finki.test.service;

import mk.ukim.finki.test.model.User;
import mk.ukim.finki.test.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
    UserDetails loadUserByUsername(String s);
    User create(String username, String password, Role role);
}
