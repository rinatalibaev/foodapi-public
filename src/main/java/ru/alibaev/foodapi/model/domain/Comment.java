package ru.alibaev.foodapi.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.alibaev.foodapi.model.domain.base.BaseDomain;

@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseDomain {
    private User author;
    private Image image;
    private String text;
}

