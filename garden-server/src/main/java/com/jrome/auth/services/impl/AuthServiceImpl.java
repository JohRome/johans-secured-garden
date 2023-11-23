package com.jrome.auth.services.impl;

import com.jrome.auth.entities.Role;
import com.jrome.auth.entities.User;
import com.jrome.auth.payloads.LoginDTO;
import com.jrome.auth.payloads.RegisterDTO;
import com.jrome.auth.repositories.RoleRepository;
import com.jrome.auth.repositories.UserRepository;
import com.jrome.auth.security.JWTTokenProvider;
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
    private final JWTTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDTO dto) {
        //TODO: Maybe change the response if wrong credentials are used?
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsernameOrEmail(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateJwtToken(authentication);
    }

    @Override
    public String register(RegisterDTO dto) {
        //TODO: If the same user username or email exists, handle exceptions better
        if(userRepository.existsByUsername(dto.getUsername()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username already exists");

        if(userRepository.existsByEmail(dto.getEmail()))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Email already exists");

        var gardenMaster = new User();
        gardenMaster.setName(dto.getName());
        gardenMaster.setUsername(dto.getUsername());
        gardenMaster.setEmail(dto.getEmail());
        gardenMaster.setPassword(passwordEncoder.encode(dto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role gardenMasterRole = roleRepository.findByName("ROLE_GARDEN_MASTER").get();
        roles.add(gardenMasterRole);
        gardenMaster.setRoles(roles);

        userRepository.save(gardenMaster);

        return "Garden Master registered successfully!";
    }
}
