package pl.mikolajp.forum.model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.mikolajp.forum.model.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUserName(String username) {
        TypedQuery<User> query = entityManager.createQuery("from User where username=:uname and enabled=true", User.class);
        query.setParameter("uname", username);

        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }

        return user;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.merge(user);
    }
}
