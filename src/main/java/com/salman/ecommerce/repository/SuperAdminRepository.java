package com.salman.ecommerce.repository;

import com.salman.ecommerce.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
    Optional<SuperAdmin> findByUsername(String username);
    boolean existsByEmail(String email);


}
