package ru.alibaev.foodapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alibaev.foodapi.controller.api.PreparedApi;
import ru.alibaev.foodapi.model.dto.request.PreparedRequest;
import ru.alibaev.foodapi.service.PreparedService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prepared")
public class PreparedController implements PreparedApi {

    private final PreparedService preparedService;

    @PostMapping
    @Override
    public void incrementPreparedCount(@RequestBody PreparedRequest preparedRequest) {
        preparedService.incrementPreparedCount(preparedRequest.getRecipeUuid());
    }

}

