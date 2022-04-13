package mk.ukim.finki.test.repository;

import mk.ukim.finki.test.model.User;
import mk.ukim.finki.test.model.enumerations.Role;
import mk.ukim.finki.test.model.projections.UserProjection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        List<User> users = this.userRepository.findAll();
        Assert.assertEquals(1L, users.size());
    }

    @Test
    public void testFetchAll() {
        List<User> users = this.userRepository.fetchAll();
        Assert.assertEquals(1L, users.size());
    }

    @Test
    public void testLoadAll() {
        List<User> users = this.userRepository.loadAll();
        Assert.assertEquals(1L, users.size());
    }

    @Test
    public void testProjectUsernameAndNameAndSurname() {
        UserProjection user = this.userRepository.findByRole(Role.ROLE_USER);
        Assert.assertEquals("elena.spirkoska", user.getUsername());
        Assert.assertEquals("Elena", user.getName());
        Assert.assertEquals("Spirkoska", user.getSurname());
    }
}
