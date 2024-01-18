package pl.mikolajp.forum.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
class UserRestController {
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<UserDao> getAllUsersOrByName(@RequestParam(required = false) Optional<String> name){
        if (name.isPresent()){
            return userService.getUserByUsername(name);
        }
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDao getUser(@PathVariable Integer id){
        Optional<UserDao> result = userService.getUser(id);
        return result.orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateUser(@RequestBody UserDto userDto, @PathVariable Integer id){
        userService.updateUser(userDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}
