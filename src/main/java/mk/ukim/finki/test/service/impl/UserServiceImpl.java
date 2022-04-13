package mk.ukim.finki.test.service.impl;

import mk.ukim.finki.test.model.User;
import mk.ukim.finki.test.model.enumerations.Role;
import mk.ukim.finki.test.model.exceptions.InvalidUsernameOrPassword;
import mk.ukim.finki.test.model.exceptions.PasswordsDoNotMatch;
import mk.ukim.finki.test.model.exceptions.UsernameExists;
import mk.ukim.finki.test.repository.UserRepository;
import mk.ukim.finki.test.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }

    @Override
    public User create(String username, String password, Role role) {
        return null;
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if(username == null || username.isEmpty() || password.isEmpty() || password == null) {
            throw new InvalidUsernameOrPassword();
        }
        if(this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameExists();
        }
        if(!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatch();
        }

        User user = new User(name,surname,username,passwordEncoder.encode(password),role);
        return this.userRepository.save(user);
    }
}