package pl.mikolajp.forum.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class RoleRepository {
    private final IRoleRepository iRoleRepository;

    List<RoleDao> findAllRoles() {
        return iRoleRepository.findAll();
    }

    Optional<RoleDao> findRoleById(Integer id) {
        return iRoleRepository.findById(id);
    }
}
