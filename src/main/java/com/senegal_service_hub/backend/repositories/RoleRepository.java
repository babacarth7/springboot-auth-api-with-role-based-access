package com.senegal_service_hub.backend.repositories;

import com.senegal_service_hub.backend.entities.RoleEntity;
import com.senegal_service_hub.backend.entities.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRole(RoleEnum role);
}
