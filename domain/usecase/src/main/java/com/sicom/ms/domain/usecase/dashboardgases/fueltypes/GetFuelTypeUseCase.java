package com.sicom.ms.domain.usecase.dashboardgases.fueltypes;

import com.sicom.ms.domain.model.dashboardgases.fueltypes.FuelType;
import com.sicom.ms.domain.model.dashboardgases.fueltypes.FuelTypesGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetFuelTypeUseCase {

    private final FuelTypesGateway fuelTypesGateway;

    public Flux<FuelType> getByFuelCode(String fuelCode){
        return fuelTypesGateway.getByFuelType(fuelCode);
    }
}
