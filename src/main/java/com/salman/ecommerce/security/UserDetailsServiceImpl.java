package com.salman.ecommerce.security;

import com.salman.ecommerce.entity.Admin;
import com.salman.ecommerce.entity.Customer;
import com.salman.ecommerce.entity.Seller;
import com.salman.ecommerce.entity.SuperAdmin;
import com.salman.ecommerce.repository.AdminRepository;
import com.salman.ecommerce.repository.CustomerRepository;
import com.salman.ecommerce.repository.SellerRepository;
import com.salman.ecommerce.repository.SuperAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;
    private final SuperAdminRepository superAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isPresent()) {
            return customer.get();
        }

        Optional<Seller> seller = sellerRepository.findByUsername(username);
        if (seller.isPresent()) {
            return seller.get();
        }

        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isPresent()) {
            return admin.get();
        }


        Optional<SuperAdmin> superAdmin = superAdminRepository.findByUsername(username); // 👈 Add this
        if (superAdmin.isPresent()) {
            return superAdmin.get(); // 👈 Add this
        }

        throw new UsernameNotFoundException("User not found");
    }
}
