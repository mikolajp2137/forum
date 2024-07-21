package pl.mikolajp.forum.model.dao;

import pl.mikolajp.forum.model.entity.User;

public interface UserDao {
    User findByUserName(String username);

    void save(User user);
}
