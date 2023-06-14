package com.sicom.ms.infrastructure.web.providers;

import com.sicom.ms.domain.model.plants.Plant;
import com.sicom.ms.domain.model.providers.Provider;
import com.sicom.ms.domain.usecase.plants.GetPlantsByAgentIdUseCase;
import com.sicom.ms.domain.usecase.providers.GetProvidersBySicomCodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class ProvidersController {
    private final GetProvidersBySicomCodeUseCase getProvidersBySicomCodeUseCase;

    @GetMapping()
    public Flux<Provider> get(@RequestParam  String sicomCode) {
        return getProvidersBySicomCodeUseCase.get(sicomCode);
    }
}
