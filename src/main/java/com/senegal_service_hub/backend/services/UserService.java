package com.senegal_service_hub.backend.services;

import com.senegal_service_hub.backend.dtos.SignUpUserDto;
import com.senegal_service_hub.backend.entities.RoleEntity;
import com.senegal_service_hub.backend.entities.RoleEnum;
import com.senegal_service_hub.backend.entities.UserEntity;
import com.senegal_service_hub.backend.repositories.RoleRepository;
import com.senegal_service_hub.backend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserEntity> findAll() {
        List<UserEntity> userEntities = new ArrayList<>();
        userRepository.findAll().forEach(userEntities::add);
        return userEntities;
    }

    public UserEntity signUpUserAdministrator(SignUpUserDto signUpUserDto) {
        Optional<RoleEntity> roleEntityOptional = roleRepository.findByRole(RoleEnum.ADMIN);

        if (roleEntityOptional.isEmpty()) {
            return null;
        }

        var userEntity = new UserEntity();
        userEntity.setEmail(signUpUserDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(signUpUserDto.getPassword()));
        userEntity.setRole(roleEntityOptional.get());

        return userRepository.save(userEntity);
    }

    public UserEntity signUpUserSuperAdministrator(SignUpUserDto signUpUserDto) {
        Optional<RoleEntity> roleEntityOptional = roleRepository.findByRole(RoleEnum.SUPER_ADMIN);

        if (roleEntityOptional.isEmpty()) {
            return null;
        }

        var userEntity = new UserEntity();
        userEntity.setEmail(signUpUserDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(signUpUserDto.getPassword()));
        userEntity.setRole(roleEntityOptional.get());

        return userRepository.save(userEntity);
    }
}
