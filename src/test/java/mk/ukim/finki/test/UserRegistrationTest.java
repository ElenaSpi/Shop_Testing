package mk.ukim.finki.test;

import mk.ukim.finki.test.model.User;
import mk.ukim.finki.test.model.enumerations.Role;
import mk.ukim.finki.test.model.exceptions.InvalidArguments;
import mk.ukim.finki.test.model.exceptions.InvalidUsernameOrPassword;
import mk.ukim.finki.test.model.exceptions.PasswordsDoNotMatch;
import mk.ukim.finki.test.model.exceptions.UsernameExists;
import mk.ukim.finki.test.repository.UserRepository;
import mk.ukim.finki.test.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");
        service = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }


    @Test
    public void testSuccessRegister() {
        User user = this.service.register("username","password","password","name","surname", Role.ROLE_USER);
        Mockito.verify(this.service).register("username","password","password","name","surname", Role.ROLE_USER);

        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("Name does not match", "name", user.getName());
        Assert.assertEquals("Password does not match", "password", user.getPassword());
        Assert.assertEquals("Username does not match", "username", user.getUsername());
        Assert.assertEquals("Surname does not match", "surname", user.getSurname());
        Assert.assertEquals("Role does not match", Role.ROLE_USER, user.getRole());
    }

    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidUsernameOrPassword expected",
                InvalidUsernameOrPassword.class,
                () -> this.service.register(null, "password","password","name","surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(null,"password","password","name","surname", Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidUsernameOrPassword expected",
                InvalidUsernameOrPassword.class,
                () -> this.service.register(username, "password","password","name","surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(username,"password","password","name","surname", Role.ROLE_USER);
    }

    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidUsernameOrPassword expected",
                InvalidUsernameOrPassword.class,
                () -> this.service.register(username, password,"password","name","surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(username,password,"password","name","surname", Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String password = null;
        Assert.assertThrows("NullPointerException expected",
                NullPointerException.class,
                () -> this.service.register("username", null,"password","name","surname", Role.ROLE_USER));
        Mockito.verify(this.service).register("username",null,"password","name","surname", Role.ROLE_USER);
    }

    @Test
    public void testPasswordsMismatch() {
        String username = "username";
        String password = "password";
        String repeatPassword = "other";
        Assert.assertThrows("PasswordsDoNotMatch expected",
                PasswordsDoNotMatch.class,
                () -> this.service.register(username, password,repeatPassword,"name","surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(username,password,repeatPassword,"name","surname", Role.ROLE_USER);
    }

    @Test
    public void testDuplicateUsername() {
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameExists expected", UsernameExists.class,
                () -> this.service.register("username", "password","repeatPassword","name","surname", Role.ROLE_USER));
        Mockito.verify(this.service).register("username","password","repeatPassword","name","surname", Role.ROLE_USER);
    }

}