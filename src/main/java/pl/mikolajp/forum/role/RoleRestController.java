package pl.mikolajp.forum.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
class RoleRestController {
    private final RoleService roleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<RoleDao> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    RoleDao getRoleById(@PathVariable Integer id){
        Optional<RoleDao> result = roleService.getRole(id);
        return result.orElse(null);
    }
}
