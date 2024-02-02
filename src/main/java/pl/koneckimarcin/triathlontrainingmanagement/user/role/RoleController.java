package pl.koneckimarcin.triathlontrainingmanagement.user.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController implements RoleOperations {

    @Autowired
    private RoleService roleService;

    @Override
    public void addRoleToUserById(Long id, Role role) {

        roleService.addRoleToUserById(id, role);
    }
}
