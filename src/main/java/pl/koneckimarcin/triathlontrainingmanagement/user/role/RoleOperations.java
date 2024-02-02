package pl.koneckimarcin.triathlontrainingmanagement.user.role;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface RoleOperations {

    @PutMapping("/users/{id}/roles")
    public void addRoleToUserById(@PathVariable Long id, @RequestParam Role role);
}
