package com.sicom.ms.infrastructure.web.dashboard.certification;

import com.sicom.ms.domain.model.dashboardgases.garagecoordinates.GarageCoordinatesFilters;
import com.sicom.ms.domain.model.dashboardgases.garagecoordinates.GarageCoordinatesResponse;
import com.sicom.ms.domain.usecase.dashboardgases.garagecoordinates.GetGarageCoordinatesBySicomCodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/dashboard-gases/certification")
@RequiredArgsConstructor
public class GarageCoordinatesController {

    private final GetGarageCoordinatesBySicomCodeUseCase getGarageCoordinatesBySicomCodeUseCase;

    @GetMapping(value = "/garage-location")
    public Flux<GarageCoordinatesResponse> getBySicomCode(@RequestParam String codigoSicomTallerRevision) {

        return getGarageCoordinatesBySicomCodeUseCase.getBySicomCode(GarageCoordinatesFilters.builder().sicomCode(codigoSicomTallerRevision).build());
    }
}
