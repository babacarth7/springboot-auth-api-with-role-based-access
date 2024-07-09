package com.senegal_service_hub.backend.bootstrap;

import com.senegal_service_hub.backend.dtos.SignUpUserDto;
import com.senegal_service_hub.backend.entities.RoleEntity;
import com.senegal_service_hub.backend.entities.RoleEnum;
import com.senegal_service_hub.backend.entities.UserEntity;
import com.senegal_service_hub.backend.repositories.RoleRepository;
import com.senegal_service_hub.backend.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperUserAdmin();
    }

    private void createSuperUserAdmin() {
        SignUpUserDto signUpUserDto = new SignUpUserDto();
        signUpUserDto.setName("Super Admin");
        signUpUserDto.setEmail("admin@admin.com");
        signUpUserDto.setMobile("221771009090");
        signUpUserDto.setPassword("admin123");

        Optional<UserEntity> userEntity = userRepository.findByEmail(signUpUserDto.getEmail());
        Optional<RoleEntity> roleEntity = roleRepository.findByRole(RoleEnum.SUPER_ADMIN);

        if (roleEntity.isEmpty() || userEntity.isPresent()) {
            return;
        }

        var superUser = new UserEntity();
        superUser.setName(signUpUserDto.getName());
        superUser.setEmail(signUpUserDto.getEmail());
        superUser.setMobile(signUpUserDto.getMobile());
        superUser.setPassword(passwordEncoder.encode(signUpUserDto.getPassword()));
        superUser.setRole(roleEntity.get());
        userRepository.save(superUser);
    }
}
