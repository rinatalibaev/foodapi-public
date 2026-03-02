package ru.alibaev.foodapi.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.alibaev.foodapi.model.dto.response.ImageResponse;

import java.io.IOException;
import java.util.UUID;

@Tag(name = "Images", description = "Загрузка и получение изображений")
public interface ImageApi {

    @Operation(summary = "Загрузить изображение")
    ImageResponse uploadImage(
            @Parameter(description = "Файл изображения", required = true)
            MultipartFile file
    ) throws IOException;

    @Operation(summary = "Скачать изображение")
    ResponseEntity<Resource> downloadImage(
            @Parameter(description = "UUID изображения", required = true)
            UUID imageUuid
    );
}
