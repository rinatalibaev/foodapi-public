package ru.alibaev.foodapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.alibaev.foodapi.config.provider.UserUuidProvider;
import ru.alibaev.foodapi.controller.api.RecipeApi;
import ru.alibaev.foodapi.mapper.RecipeMapper;
import ru.alibaev.foodapi.mapper.UserMapper;
import ru.alibaev.foodapi.model.domain.Ingredient;
import ru.alibaev.foodapi.model.domain.MeasureUnit;
import ru.alibaev.foodapi.model.domain.Recipe;
import ru.alibaev.foodapi.model.domain.Step;
import ru.alibaev.foodapi.model.domain.junction.RecipeIngredient;
import ru.alibaev.foodapi.model.dto.request.RecipeCreateRequest;
import ru.alibaev.foodapi.model.dto.response.RecipeCreateResponse;
import ru.alibaev.foodapi.model.dto.response.RecipeListResponse;
import ru.alibaev.foodapi.model.dto.response.RecipeResponse;
import ru.alibaev.foodapi.repository.UserRepository;
import ru.alibaev.foodapi.service.RecipeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
public class RecipeController implements RecipeApi {

    private final UserRepository userRepository;
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;
    private final UserMapper userMapper;
    private final UserUuidProvider userUuidProvider;

    @Override
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public RecipeCreateResponse createRecipe(@RequestBody RecipeCreateRequest request) {
        Recipe recipe = new Recipe();
        recipe.setName(request.getName());

        userRepository.findByUuidAndDeletedAtIsNull(userUuidProvider.provide())
                .ifPresent(authorEntity -> recipe.setAuthor(userMapper.toDomain(authorEntity)));

        AtomicInteger duration = new AtomicInteger();
        if (request.getSteps() != null) {
            List<Step> steps = request.getSteps().stream().map(s -> {
                Step step = new Step();
                step.setStepOrder(s.getStepOrder());
                step.setName(s.getName());
                step.setDuration(s.getDuration());
                duration.addAndGet(s.getDuration());
                return step;
            }).toList();
            recipe.setSteps(steps);
        }
        recipe.setDuration(duration.get());

        if (request.getIngredients() != null) {
            List<RecipeIngredient> ingredients = request.getIngredients().stream().map(riReq -> {
                RecipeIngredient ri = new RecipeIngredient();
                ri.setCount(riReq.getCount());
                ri.setIngredient(new Ingredient() {{ setUuid(riReq.getIngredientUuid()); }});
                ri.setMeasureUnit(new MeasureUnit() {{ setUuid(riReq.getMeasureUnitUuid()); }});
                return ri;
            }).toList();
            recipe.setIngredients(ingredients);
        }

        UUID recipeUuid = recipeService.createRecipe(recipe, request.getPhotoUuid());

        RecipeCreateResponse response = new RecipeCreateResponse();
        response.setUuid(recipeUuid);
        return response;
    }

    @Override
    @GetMapping
    public List<RecipeListResponse> getAllRecipes(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return recipeService.getAllRecipes(pageable).stream()
                .map(recipeMapper::toListResponse)
                .toList();
    }

    @Override
    @GetMapping("/{uuid}")
    public RecipeResponse getRecipeByUuid(@PathVariable UUID uuid) {
        return Optional.ofNullable(recipeService.getRecipeByUuid(uuid))
                .map(recipeMapper::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    @DeleteMapping("/{uuid}")
    public void deleteRecipe(@PathVariable UUID uuid) {
        recipeService.softDeleteRecipe(uuid);
    }

}

