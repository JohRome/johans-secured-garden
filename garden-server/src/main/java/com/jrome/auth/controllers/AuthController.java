package com.jrome.auth.controllers;

import com.jrome.auth.payloads.JWTAuthResponse;
import com.jrome.auth.payloads.LoginDTO;
import com.jrome.auth.payloads.RegisterDTO;
import com.jrome.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling authentication-related requests.
 */
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * Handles the HTTP POST request for user login.
     *
     * @param dto The LoginDTO containing user credentials.
     * @return ResponseEntity containing a JWTAuthResponse with the access token.
     */
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO dto) {
        String token = authService.login(dto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    /**
     * Handles the HTTP POST request for user registration.
     *
     * @param dto The RegisterDTO containing user registration details.
     * @return ResponseEntity with a string response and HTTP status code 201 (Created).
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {

        String response = authService.register(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
