package ru.alibaev.foodapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alibaev.foodapi.model.entity.junction.FavoriteEntity;
import ru.alibaev.foodapi.model.entity.junction.PreparedEntity;

import java.util.List;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
    List<FavoriteEntity> findAllByUser_Uuid(UUID userUuid);

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO favorite (user_id, recipe_id)
        SELECT u.id, r.id
        FROM app_user u, recipe r
        WHERE u.uuid = :userUuid
          AND r.uuid = :recipeUuid
        """, nativeQuery = true)
    void addFavorite(
            @Param("userUuid") UUID userUuid,
            @Param("recipeUuid") UUID recipeUuid
    );

    void deleteByUser_UuidAndRecipe_Uuid(UUID userUuid, UUID recipeUuid);

    List<FavoriteEntity> findAllByUserUuid(UUID userUuid);
}

