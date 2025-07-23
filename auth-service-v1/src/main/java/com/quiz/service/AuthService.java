package com.quiz.service;

import lombok.RequiredArgsConstructor;
import com.quiz.dto.AuthResponse;
import com.quiz.dto.LoginRequest;
import com.quiz.dto.RegisterRequest;
import com.quiz.entity.Role;
import com.quiz.entity.User;
import com.quiz.repository.UserRepository;
import com.quiz.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);

        // generating the JWT token and adding Role as extra claim
        String jwtToken = jwtService.generateToken(java.util.Map.of("role", user.getRole()), user.getEmail());
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(LoginRequest request){
        // using authenticationManager from SecurityConfig to authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail())  .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(java.util.Map.of("role", user.getRole()), user.getEmail());
        return new AuthResponse(token);
    }


}
