package com.and.pizzas.web.controller;

import com.and.pizzas.services.dto.LoginDto;
import com.and.pizzas.web.security.JsonWebTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JsonWebTokenUtils jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Void> login (@RequestBody LoginDto loginDto){
        try {
            Authentication login = new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(), loginDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(login);

            if (authentication.isAuthenticated()) {
                String jwt = jwtUtil.create(loginDto.getUsername());
                return ResponseEntity.ok()
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                        .build();
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }catch (BadCredentialsException e)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
