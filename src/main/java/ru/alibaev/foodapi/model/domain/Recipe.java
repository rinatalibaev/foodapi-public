package ru.alibaev.foodapi.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.alibaev.foodapi.model.domain.base.BaseDomain;
import ru.alibaev.foodapi.model.domain.junction.RecipeIngredient;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Recipe extends BaseDomain {
    private String name;
    private Integer duration;
    private User author;
    private Image image;
    private List<Step> steps;
    private List<RecipeIngredient> ingredients;
    private Set<Comment> comments;
    private boolean favorite;
    private int preparedCount;

}

