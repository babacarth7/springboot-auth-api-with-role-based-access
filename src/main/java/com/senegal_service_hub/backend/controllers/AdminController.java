package com.senegal_service_hub.backend.controllers;

import com.senegal_service_hub.backend.dtos.SignUpUserDto;
import com.senegal_service_hub.backend.entities.UserEntity;
import com.senegal_service_hub.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/admins")
@RestController
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<UserEntity> signUpUserAdmin(@RequestBody SignUpUserDto signUpUserDto) {
        UserEntity userEntity = userService.signUpUserAdministrator(signUpUserDto);
        return ResponseEntity.ok(userEntity);
    }

    @PostMapping("/super-admin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<UserEntity> signUpUserSuperAdmin(@RequestBody SignUpUserDto signUpUserDto) {
        UserEntity userEntity = userService.signUpUserSuperAdministrator(signUpUserDto);
        return ResponseEntity.ok(userEntity);
    }
}
