package mk.ukim.finki.test.repository;

import mk.ukim.finki.test.model.User;
import mk.ukim.finki.test.model.enumerations.Role;
import mk.ukim.finki.test.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
    UserProjection findByRole(Role role);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
                attributePaths = {"carts"})
    @Query("select u from User u")
    List<User> fetchAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,
                attributePaths = {"carts", "discount"})
    @Query("select u from User u")
    List<User> loadAll();

    @Query("select u.username, u.name, u.surname from User u")
    List<UserProjection> takeUsernameAndNameAndSurnameByProjection();
}
