package pl.mikolajp.forum.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotNull(message = "Username is required!")
    @Size(min = 3, message = "Username needs to be at least 3 characters long!")
    @Size(max = 254, message = "Username cannot be longer than 254 characters!")
    private String username;

    @NotNull(message = "Password is required!")
    @Size(min = 8, message = "Password needs to be at least 8 characters long!")
    @Size(max = 254, message = "Password cannot be longer than 254 characters!")
    private String password;
}
