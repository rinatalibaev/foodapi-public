package ru.alibaev.foodapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.alibaev.foodapi.model.entity.base.BaseEntity;

@Entity
@Table(name = "app_user")
@Getter
@Setter
public class AppUserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String firstName;

    @Column()
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_image_id")
    private ImageEntity avatar;

    @Column(name = "keycloak_id", unique = true)
    private String keycloakId;
}