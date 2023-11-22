package com.jrome.auth.services.impl;

import com.jrome.auth.entities.Role;
import com.jrome.auth.entities.User;
import com.jrome.auth.payloads.LoginDTO;
import com.jrome.auth.payloads.RegisterDTO;
import com.jrome.auth.repositories.RoleRepository;
import com.jrome.auth.repositories.UserRepository;
import com.jrome.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginDTO dto) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsernameOrEmail(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "Logged in successfully";
    }

    @Override
    public String register(RegisterDTO dto) {

        if(userRepository.existsByUsername(dto.getUsername()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username already exists");

        if(userRepository.existsByEmail(dto.getEmail()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Email already exists");

        var admin = new User();
        admin.setName(dto.getName());
        admin.setUsername(dto.getUsername());
        admin.setEmail(dto.getEmail());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
        roles.add(adminRole);
        admin.setRoles(roles);

        userRepository.save(admin);

        return "Admin registered successfully!";
    }
}