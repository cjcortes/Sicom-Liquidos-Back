package com.sicom.ms.domain.usecase.dashboardgases.garagecoordinates;

import com.sicom.ms.domain.model.dashboardgases.garagecoordinates.GarageCoordinatesFilters;
import com.sicom.ms.domain.model.dashboardgases.garagecoordinates.GarageCoordinatesGateway;
import com.sicom.ms.domain.model.dashboardgases.garagecoordinates.GarageCoordinatesResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetGarageCoordinatesBySicomCodeUseCase {

    private final GarageCoordinatesGateway garageCoordinatesGateway;

    public Flux<GarageCoordinatesResponse> getBySicomCode(GarageCoordinatesFilters filters){
        return garageCoordinatesGateway.getBySicomCode(filters);
    }
}
