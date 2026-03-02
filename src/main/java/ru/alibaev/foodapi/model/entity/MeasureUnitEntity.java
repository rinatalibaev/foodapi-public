package ru.alibaev.foodapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.alibaev.foodapi.model.entity.base.BaseEntity;

@Entity
@Table(name = "measure_unit")
@Getter
@Setter
public class MeasureUnitEntity extends BaseEntity implements ManyFieldable {

    @Column(nullable = false, unique = true)
    private String one;

    @Column
    private String few;

    @Column
    private String many;
}

