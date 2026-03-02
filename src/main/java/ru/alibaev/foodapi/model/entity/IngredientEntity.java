package ru.alibaev.foodapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.alibaev.foodapi.model.entity.base.BaseEntity;

@Entity
@Table(name = "ingredient")
@Getter
@Setter
public class IngredientEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
}

