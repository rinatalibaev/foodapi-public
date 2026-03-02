package ru.alibaev.foodapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alibaev.foodapi.model.entity.AppUserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<AppUserEntity, Long> {
    Optional<AppUserEntity> findByUuid(UUID uuid);
    Optional<AppUserEntity> findByUuidAndDeletedAtIsNull(UUID uuid);
}
