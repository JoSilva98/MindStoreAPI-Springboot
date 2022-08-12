package MindStore.persistence.repositories.Person;

import MindStore.persistence.models.Person.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users " +
            "WHERE users.first_name LIKE %:name% " +
            "OR users.last_name LIKE %:name%", nativeQuery = true)
    List<User> findByName(String name);

    Optional<User> findByEmail(String email);
}
