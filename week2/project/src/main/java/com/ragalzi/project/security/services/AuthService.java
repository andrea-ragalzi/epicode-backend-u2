package com.ragalzi.project.security.services;

import com.ragalzi.project.security.payloads.LoginDto;
import com.ragalzi.project.security.payloads.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
