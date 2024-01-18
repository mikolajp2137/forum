package pl.mikolajp.forum.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class UserDto {
    private String username;
    private String email;
    private String password;
    private Integer role_id;
}
