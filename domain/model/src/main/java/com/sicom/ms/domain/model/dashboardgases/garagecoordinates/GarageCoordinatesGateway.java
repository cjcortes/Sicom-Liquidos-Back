package com.sicom.ms.domain.model.dashboardgases.garagecoordinates;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GarageCoordinatesGateway {
    Flux<GarageCoordinatesResponse> getBySicomCode(GarageCoordinatesFilters request);
}
