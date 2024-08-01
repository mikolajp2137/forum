package pl.mikolajp.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mikolajp.forum.model.entity.Role;
import pl.mikolajp.forum.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
