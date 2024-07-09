package com.senegal_service_hub.backend.controllers;

import com.senegal_service_hub.backend.dtos.SignInUserDto;
import com.senegal_service_hub.backend.dtos.SignUpUserDto;
import com.senegal_service_hub.backend.entities.UserEntity;
import com.senegal_service_hub.backend.responses.SignInResponse;
import com.senegal_service_hub.backend.services.AuthenticationService;
import com.senegal_service_hub.backend.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signUp(@RequestBody SignUpUserDto signUpUserDto) {
        UserEntity userEntity = authenticationService.signUp(signUpUserDto);
        return ResponseEntity.ok(userEntity);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInUserDto signInUserDto) {
        UserEntity userEntity = authenticationService.signIn(signInUserDto);
        String token = jwtService.generateToken(userEntity);

        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setToken(token);
        signInResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(signInResponse);
    }
}
