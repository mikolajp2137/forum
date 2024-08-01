package pl.mikolajp.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mikolajp.forum.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
