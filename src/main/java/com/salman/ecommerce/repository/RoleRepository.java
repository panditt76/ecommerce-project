package com.salman.ecommerce.repository;

import com.salman.ecommerce.entity.Role;
import com.salman.ecommerce.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}
