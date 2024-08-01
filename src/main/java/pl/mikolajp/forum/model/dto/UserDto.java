package pl.mikolajp.forum.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotNull(message = "Username is required")
    @Size(min = 1, message = "Username is required")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 1, message = "Password is required")
    private String password;
}
