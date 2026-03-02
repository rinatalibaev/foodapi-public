package ru.alibaev.foodapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.alibaev.foodapi.mapper.ImageMapper;
import ru.alibaev.foodapi.model.domain.Image;
import ru.alibaev.foodapi.model.entity.ImageEntity;
import ru.alibaev.foodapi.repository.ImageRepository;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final S3Service s3Service;

    @Transactional
    public Image upload(MultipartFile file) throws IOException {
        String key = "images/" + UUID.randomUUID();

        String link = s3Service.upload(file, key);

        Image image = new Image();
        image.setLink(link);

        ImageEntity saved = imageRepository.save(imageMapper.toEntity(image));
        return imageMapper.toDomain(saved);
    }

    public ResponseInputStream<GetObjectResponse> download(UUID imageUuid) {
        ImageEntity image = imageRepository.findByUuid(imageUuid)
                .orElseThrow(() -> new EntityNotFoundException("Image not found"));

        String key = extractKeyFromLink(image.getLink());
        return s3Service.download(key);
    }

    @Transactional
    public void delete(UUID imageUuid) {
        imageRepository.findByUuid(imageUuid).ifPresent(entity -> {
            String key = extractKeyFromLink(entity.getLink());
            s3Service.delete(key);
            entity.markDeleted();
        });
    }

    private String extractKeyFromLink(String link) {
        int index = link.indexOf("/", link.indexOf("://") + 3);
        return link.substring(index + 1);
    }
}
