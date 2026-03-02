package ru.alibaev.foodapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alibaev.foodapi.controller.api.FreezerApi;
import ru.alibaev.foodapi.mapper.FreezerMapper;
import ru.alibaev.foodapi.model.dto.request.FreezerChangeRequest;
import ru.alibaev.foodapi.model.dto.response.FreezerResponse;
import ru.alibaev.foodapi.service.FreezerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/freezer")
public class FreezerController implements FreezerApi {

    private final FreezerService freezerService;
    private final FreezerMapper freezerMapper;

    @Override
    @PostMapping("/add")
    public void addIngredient(@RequestBody FreezerChangeRequest request) {
        freezerService.addIngredient(request);
    }

    @Override
    @PostMapping("/subtract")
    public void subtractIngredient(@RequestBody FreezerChangeRequest request) {
        freezerService.subtractIngredient(request);
    }

    @Override
    @GetMapping
    public List<FreezerResponse> getMyFreezer() {
        return freezerService.getUserFreezer().stream()
                .map(freezerMapper::toResponse)
                .toList();
    }
}

