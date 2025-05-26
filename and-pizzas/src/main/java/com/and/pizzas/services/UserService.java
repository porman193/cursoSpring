package com.and.pizzas.services;

import com.and.pizzas.persistance.entity.UserEntity;
import com.and.pizzas.persistance.entity.UserRole;
import com.and.pizzas.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        String[] roles = user.getRoles()
                    .stream()
                    .map(role -> role.getId().getRole())
                    .toArray(String[]::new);

        List<GrantedAuthority> authorities = grantedAuthorities(roles);

        return User.builder()
                        .username(user.getUserName())
                        .password(user.getPassword())
                        .disabled(user.getAccountDisabled())
                        .accountLocked(user.getAccountDisabled())
                        .authorities(authorities)
                        .build();
    }

    private List<GrantedAuthority> grantedAuthorities(String[] roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for(String role:roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));

            for(String permission : getAuthorities(role)){
                authorities.add(new SimpleGrantedAuthority(permission));
            }
        }
        return  authorities;
    }

    private String[] getAuthorities(String role) {
        return switch (role) {
            case "admin" -> new String[]{"randomOrder", "deleteUser", "viewAllOrders"};
            case "customer" -> new String[]{"randomOrder", "placeOrder"};
            default -> new String[]{};
        };
    }


}
