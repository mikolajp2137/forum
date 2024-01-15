package pl.mikolajp.forum.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    String login;
    String email;
    String password;
    Long role_id;
}
