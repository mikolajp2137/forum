package pl.mikolajp.forum.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = new UserMapper();


    List<UserDao> getAllUsers(){
        return userRepository.findAllUsers();
    }

    public void addUser(UserDto userDto){
        userRepository.saveUser(userMapper.mapDtoToDao(userDto));
    }

    Optional<UserDao> getUser(Integer id) {
        return userRepository.findById(id);
    }

    List<UserDao> getUserByUsername(Optional<String> username) {
        return userRepository.findByUsername(username);
    }

    void deleteUser(Integer id) {
        userRepository.deleteUser(id);
    }

    public void updateUser(UserDto userDto, Integer id) {
        userRepository.saveUser(userMapper.mapDtoToDao(userDto, id));
    }
}
