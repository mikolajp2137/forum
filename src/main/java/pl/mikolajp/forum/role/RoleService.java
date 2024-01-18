package pl.mikolajp.forum.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class RoleService {
    private final RoleRepository roleRepository;


    List<RoleDao> getAllRoles() {
        return roleRepository.findAllRoles();
    }

    Optional<RoleDao> getRole(Integer id) {
        return roleRepository.findRoleById(id);
    }
}
