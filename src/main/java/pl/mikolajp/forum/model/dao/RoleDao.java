package pl.mikolajp.forum.model.dao;

import pl.mikolajp.forum.model.entity.Role;

public interface RoleDao {
    public Role findRoleByName(String roleName);
}
