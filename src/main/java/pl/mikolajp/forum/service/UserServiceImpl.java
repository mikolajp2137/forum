package pl.mikolajp.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mikolajp.forum.model.dao.RoleDao;
import pl.mikolajp.forum.model.dao.UserDao;
import pl.mikolajp.forum.model.dto.UserDto;
import pl.mikolajp.forum.model.entity.Role;
import pl.mikolajp.forum.model.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(true);

        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));

        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role tempRole : roles) {
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
            authorities.add(tempAuthority);
        }

        return authorities;
    }
}
