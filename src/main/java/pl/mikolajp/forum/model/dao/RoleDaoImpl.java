package pl.mikolajp.forum.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.mikolajp.forum.model.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao{
    @Autowired
    private EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        entityManager = entityManager;
    }

    @Override
    public Role findRoleByName(String roleName) {
        // retrieve/read from database using name
        TypedQuery<Role> query = entityManager.createQuery("from Role where name=:roleName", Role.class);
        query.setParameter("roleName", roleName);

        Role role = null;

        try {
            role = query.getSingleResult();
        } catch (Exception e) {
            role = null;
        }

        return role;
    }
}
