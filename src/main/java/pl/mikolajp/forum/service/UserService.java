package pl.mikolajp.forum.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.mikolajp.forum.model.dto.UserDto;
import pl.mikolajp.forum.model.entity.User;

public interface UserService extends UserDetailsService {
    public User findByUserName(String username);

    void save(UserDto userDto);
}
