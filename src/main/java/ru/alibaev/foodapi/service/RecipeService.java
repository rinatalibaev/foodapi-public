package ru.alibaev.foodapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alibaev.foodapi.config.provider.UserUuidProvider;
import ru.alibaev.foodapi.mapper.RecipeMapper;
import ru.alibaev.foodapi.model.domain.Comment;
import ru.alibaev.foodapi.model.domain.Recipe;
import ru.alibaev.foodapi.model.domain.Step;
import ru.alibaev.foodapi.model.domain.junction.RecipeIngredient;
import ru.alibaev.foodapi.model.entity.ImageEntity;
import ru.alibaev.foodapi.model.entity.IngredientEntity;
import ru.alibaev.foodapi.model.entity.MeasureUnitEntity;
import ru.alibaev.foodapi.model.entity.RecipeEntity;
import ru.alibaev.foodapi.model.entity.StepEntity;
import ru.alibaev.foodapi.model.entity.base.BaseEntity;
import ru.alibaev.foodapi.model.entity.junction.PreparedEntity;
import ru.alibaev.foodapi.model.entity.junction.RecipeIngredientEntity;
import ru.alibaev.foodapi.repository.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final MeasureUnitRepository measureUnitRepository;
    private final ImageRepository imageRepository;
    private final PreparedRepository preparedRepository;
    private final FavoriteRepository favoriteRepository;
    private final RecipeMapper recipeMapper;
    private final UserUuidProvider userUuidProvider;

    @Transactional
    public UUID createRecipe(Recipe recipeDomain, UUID photoUuid) {
        ImageEntity imageEntity = imageRepository.findByUuid(photoUuid).orElseThrow(() -> new IllegalArgumentException("Image not found"));
        RecipeEntity recipeEntity = recipeMapper.toEntity(recipeDomain);
        recipeEntity.setImage(imageEntity);

        if (recipeDomain.getSteps() != null) {
            List<StepEntity> stepEntityList = new ArrayList<>();
            for (Step step : recipeDomain.getSteps()) {
                StepEntity stepEntity = new StepEntity();
                stepEntity.setRecipe(recipeEntity);
                stepEntity.setStepOrder(step.getStepOrder());
                stepEntity.setName(step.getName());
                stepEntity.setDuration(step.getDuration());
                stepEntityList.add(stepEntity);
            }
            recipeEntity.setSteps(stepEntityList);
        }

        if (recipeDomain.getIngredients() != null) {
            Set<RecipeIngredientEntity> recipeIngredientEntityList = new HashSet<>();
            for (RecipeIngredient ri : recipeDomain.getIngredients()) {
                RecipeIngredientEntity riEntity = new RecipeIngredientEntity();
                riEntity.setRecipe(recipeEntity);
                if (ri.getIngredient() != null) {
                    IngredientEntity ingredientEntity = ingredientRepository.findByUuid(ri.getIngredient().getUuid())
                            .orElseThrow(() -> new IllegalArgumentException("Ingredient not found"));
                    riEntity.setIngredient(ingredientEntity);
                }
                if (ri.getMeasureUnit() != null) {
                    MeasureUnitEntity measureUnitEntity = measureUnitRepository.findByUuid(ri.getMeasureUnit().getUuid())
                            .orElseThrow(() -> new IllegalArgumentException("MeasureUnit not found"));
                    riEntity.setMeasureUnit(measureUnitEntity);
                }
                riEntity.setCount(ri.getCount());
                recipeIngredientEntityList.add(riEntity);
            }
            recipeEntity.setIngredients(recipeIngredientEntityList);
        }
        RecipeEntity savedRecipe = recipeRepository.save(recipeEntity);
        return savedRecipe.getUuid();
    }

    public List<Recipe> getAllRecipes(Pageable pageable) {
        var preparedEntityMap = getPreparedEntityMap();
        var favoriteRecipesUuids = getFavoriteRecipesUuids();
        return recipeRepository.findAllByDeletedAtIsNull(pageable).stream()
                .map(recipeMapper::toDomain)
                .map(recipe -> {
                    if (preparedEntityMap.get(recipe.getUuid()) != null) {
                        recipe.setPreparedCount(preparedEntityMap.get(recipe.getUuid()).getPreparedCount());
                    }
                    recipe.setFavorite(favoriteRecipesUuids.contains(recipe.getUuid()));
                    return recipe;
                })
                .toList();
    }

    public Recipe getRecipeByUuid(UUID uuid) {
        var preparedEntityMap = getPreparedEntityMap();
        var favoriteRecipesUuids = getFavoriteRecipesUuids();
        RecipeEntity recipeEntity = recipeRepository.findByUuidAndDeletedAtIsNull(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
        Recipe recipe = recipeMapper.toDomain(recipeEntity);
        if (preparedEntityMap.get(recipe.getUuid()) != null) {
            recipe.setPreparedCount(preparedEntityMap.get(recipe.getUuid()).getPreparedCount());
        }
        recipe.setFavorite(favoriteRecipesUuids.contains(recipe.getUuid()));
        removeDeletedComments(recipe);
        return recipe;
    }

    public List<Recipe> getRecipesByUuids(List<UUID> uuids) {
        List<RecipeEntity> recipeEntities = recipeRepository.findAllByUuidInAndDeletedAtIsNull(uuids);
        List<Recipe> recipes = recipeMapper.toDomainList(recipeEntities);
        recipes.forEach(this::removeDeletedComments);
        return recipes;
    }

    @Transactional
    public void softDeleteRecipe(UUID uuid) {
        recipeRepository.findByUuidAndDeletedAtIsNull(uuid)
                .ifPresent(BaseEntity::markDeleted);
    }

    private void removeDeletedComments(Recipe recipe) {
        Iterator<Comment> iterator = recipe.getComments().iterator();
        iterator.forEachRemaining((commentEntity) -> {
            if (commentEntity.getDeletedAt() != null) {
                iterator.remove();
            }
        });
    }

    private Map<UUID, PreparedEntity> getPreparedEntityMap() {
        UUID userUuid = userUuidProvider.provide();
        List<PreparedEntity> preparedEntities = preparedRepository.findAllByUserUuid(userUuid);
        return preparedEntities.stream()
                .collect(Collectors.toMap(
                        preparedEntity -> preparedEntity.getRecipe().getUuid(),
                        Function.identity()));
    }

    private List<UUID> getFavoriteRecipesUuids() {
        UUID userUuid = userUuidProvider.provide();
        return favoriteRepository.findAllByUserUuid(userUuid).stream()
                .map(favoriteEntity -> favoriteEntity.getRecipe().getUuid())
                .toList();
    }
}




