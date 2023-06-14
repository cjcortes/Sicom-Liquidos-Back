package com.sicom.ms.domain.usecase.dashboardgases.fueltypeparametric;

import com.sicom.ms.domain.model.dashboardgases.fueltypeparametric.FuelTypeParametric;
import com.sicom.ms.domain.model.dashboardgases.fueltypeparametric.FuelTypeParametricFilters;
import com.sicom.ms.domain.model.dashboardgases.fueltypeparametric.FuelTypeParametricGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetFuelTypeParametricByFiltersUseCase {

    private final FuelTypeParametricGateway fuelTypeParametricGateway;

    public Flux<FuelTypeParametric> getByFilters(FuelTypeParametricFilters filters){
        return fuelTypeParametricGateway.getFuelTypeParametricByFilters(filters);
    }
}
