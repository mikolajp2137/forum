package pl.mikolajp.forum.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class UserRepository {
    private final IUserRepository iUserRepository;

    List<UserDao> findAllUsers(){
        return iUserRepository.findAll();
    }

    void saveUser(UserDao userDao){
        iUserRepository.save(userDao);
    }

    Optional<UserDao> findById(Integer id) {
        return iUserRepository.findById(id);
    }

    List<UserDao> findByUsername(Optional<String> username) {
        return iUserRepository.findByUsername(username);
    }

    void deleteUser(Integer id) {
        iUserRepository.deleteById(id);
    }
}
