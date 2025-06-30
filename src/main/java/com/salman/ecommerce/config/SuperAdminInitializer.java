package com.salman.ecommerce.config;

import com.salman.ecommerce.entity.Role;
import com.salman.ecommerce.entity.RoleName;
import com.salman.ecommerce.entity.SuperAdmin;
import com.salman.ecommerce.repository.RoleRepository;
import com.salman.ecommerce.repository.SuperAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@DependsOn("entityManagerFactory")
public class SuperAdminInitializer implements CommandLineRunner {

    private final SuperAdminRepository superAdminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
            // Step 1: Ensure SUPERADMIN role exists
            Role superAdminRole = roleRepository.findByName(RoleName.SUPERADMIN)
                    .orElseGet(() -> {
                        // If role does not exist, create it
                        Role role = new Role();
                        role.setName(RoleName.SUPERADMIN);
                        role.setDescription("Role for SuperAdmin with full access");
                        return roleRepository.save(role);  // Save the role and return it
                    });

            // Step 2: Check if the superadmin already exists
            if (superAdminRepository.findByUsername("superadmin").isEmpty()) {
                // If SuperAdmin doesn't exist, create it
                SuperAdmin superAdmin = new SuperAdmin();
                superAdmin.setUsername("superadmin");
                superAdmin.setEmail("superadmin@admin.com");
                superAdmin.setPassword(passwordEncoder.encode("SuperAdmin@123"));
                superAdmin.setRole(superAdminRole);

                superAdminRepository.save(superAdmin);
                System.out.println("SuperAdmin created successfully.");
            }
        }
}
