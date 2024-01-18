package pl.mikolajp.forum.role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class RoleDto {
    private Integer roleId;
    private String roleName;
}
