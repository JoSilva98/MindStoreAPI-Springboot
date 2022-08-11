package MindStore.persistence.repositories;

import MindStore.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
