package com.senegal_service_hub.backend.services;

import com.senegal_service_hub.backend.dtos.SignInUserDto;
import com.senegal_service_hub.backend.dtos.SignUpUserDto;
import com.senegal_service_hub.backend.entities.RoleEntity;
import com.senegal_service_hub.backend.entities.RoleEnum;
import com.senegal_service_hub.backend.entities.UserEntity;
import com.senegal_service_hub.backend.repositories.RoleRepository;
import com.senegal_service_hub.backend.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public UserEntity signUp(SignUpUserDto input) {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByRole(RoleEnum.USER);
        if (optionalRoleEntity.isEmpty()) {
            throw new IllegalStateException("Role PROVIDER not found");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(input.getName());
        userEntity.setEmail(input.getEmail());
        userEntity.setMobile(input.getMobile());
        userEntity.setPassword(passwordEncoder.encode(input.getPassword()));
        userEntity.setRole(optionalRoleEntity.get());
        return userRepository.save(userEntity);
    }


    public UserEntity signIn(SignInUserDto input) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()));
       return userRepository.findByEmail(input.getEmail()).orElseThrow();

    }
}
