package io.github.kassymbekoff.springjwt.repositories;

import io.github.kassymbekoff.springjwt.models.ERole;
import io.github.kassymbekoff.springjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole role);
}
