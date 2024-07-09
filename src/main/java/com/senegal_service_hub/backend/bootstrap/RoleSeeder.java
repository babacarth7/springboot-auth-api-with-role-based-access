package com.senegal_service_hub.backend.bootstrap;

import com.senegal_service_hub.backend.entities.RoleEntity;
import com.senegal_service_hub.backend.entities.RoleEnum;
import com.senegal_service_hub.backend.repositories.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[]{RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN};
        Map<RoleEnum, String> roleDescriptions = Map.of(
                RoleEnum.USER, "Default user role",
                RoleEnum.ADMIN, "Admin user role",
                RoleEnum.SUPER_ADMIN, "Super admin user role");

        Arrays.stream(roleNames).forEach(role -> {
            Optional<RoleEntity> optionalRoleEntity = roleRepository.findByRole(role);
            optionalRoleEntity.ifPresentOrElse(System.out::println, () -> {
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setRole(role);
                roleEntity.setDescription(roleDescriptions.get(role));
                roleRepository.save(roleEntity);
            });
        });
    }
}
