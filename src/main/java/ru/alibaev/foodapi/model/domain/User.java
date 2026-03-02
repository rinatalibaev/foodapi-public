package ru.alibaev.foodapi.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.alibaev.foodapi.model.domain.base.BaseDomain;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseDomain {
    private String login;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private Image avatar;
}

