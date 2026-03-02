package ru.alibaev.foodapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.alibaev.foodapi.controller.api.ImageApi;
import ru.alibaev.foodapi.mapper.ImageMapper;
import ru.alibaev.foodapi.model.dto.response.ImageResponse;
import ru.alibaev.foodapi.service.ImageService;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController implements ImageApi {

    private final ImageService imageService;
    private final ImageMapper imageMapper;

    @Override
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ImageResponse uploadImage(@RequestPart("file") MultipartFile file) throws IOException {
        return imageMapper.toDto(imageService.upload(file));
    }

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<Resource> downloadImage(@PathVariable UUID uuid) {

        ResponseInputStream<GetObjectResponse> s3Object = imageService.download(uuid);
        GetObjectResponse response = s3Object.response();

        InputStreamResource resource = new InputStreamResource(s3Object);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(
                        response.contentType() != null
                                ? response.contentType()
                                : MediaType.APPLICATION_OCTET_STREAM_VALUE
                ))
                .contentLength(response.contentLength())
                .body(resource);
    }
}

