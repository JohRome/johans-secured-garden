package com.jrome.auth.services;

import com.jrome.auth.payloads.LoginDTO;
import com.jrome.auth.payloads.RegisterDTO;

public interface AuthService {
    String login(LoginDTO dto);
    String register(RegisterDTO dto);
}
