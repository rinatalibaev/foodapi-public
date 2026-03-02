package ru.alibaev.foodapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.alibaev.foodapi.model.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "image")
public class ImageEntity extends BaseEntity {

    @Column(nullable = false)
    private String link;
}
