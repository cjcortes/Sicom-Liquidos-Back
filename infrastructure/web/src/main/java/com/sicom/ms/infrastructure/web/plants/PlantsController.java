package com.sicom.ms.infrastructure.web.plants;

import com.sicom.ms.domain.model.plants.Plant;
import com.sicom.ms.domain.usecase.plants.GetAllPlantsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
public class PlantsController {
    private final GetAllPlantsUseCase getAllPlantsUseCase;

    @GetMapping(value = "/get-plants")
    public Flux<Plant> getAllPlants() {
        return getAllPlantsUseCase.get();
    }
}
