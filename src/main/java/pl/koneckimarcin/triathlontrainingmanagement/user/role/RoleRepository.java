package pl.koneckimarcin.triathlontrainingmanagement.user.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <RoleEntity, Long> {

    public RoleEntity findByRole(Role role);
}
