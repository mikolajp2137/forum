package pl.mikolajp.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mikolajp.forum.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndEnabledTrue(String username);
}
