package pl.mikolajp.forum.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface IUserRepository extends JpaRepository<UserDao, Integer> {
    List<UserDao> findByUsername(Optional<String> username);
}
