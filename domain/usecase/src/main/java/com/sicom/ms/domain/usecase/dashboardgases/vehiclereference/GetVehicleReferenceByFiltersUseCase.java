package com.sicom.ms.domain.usecase.dashboardgases.vehiclereference;

import com.sicom.ms.domain.model.dashboardgases.vehiclereference.VehicleReference;
import com.sicom.ms.domain.model.dashboardgases.vehiclereference.VehicleReferenceFilters;
import com.sicom.ms.domain.model.dashboardgases.vehiclereference.VehicleReferenceGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.dashboardgases.vehiclereference.GetVehicleReferenceByFiltersRules.GET_VEHICLE_REFERENCE_BY_FILTER_REQUEST_RULES;

@RequiredArgsConstructor
public class GetVehicleReferenceByFiltersUseCase {

    private final ObjectValidator objectValidator;

    private final VehicleReferenceGateway vehicleReferenceGateway;

    public Flux<VehicleReference> getByFilters(VehicleReferenceFilters filters){
        if(filters.getVehicleReferenceId().equals("-1")){
            objectValidator.validate(filters, GET_VEHICLE_REFERENCE_BY_FILTER_REQUEST_RULES)
                    .throwBadRequestExceptionIfInvalid("getVehicleBrandId");
            objectValidator.validate(filters, GET_VEHICLE_REFERENCE_BY_FILTER_REQUEST_RULES)
                    .throwBadRequestExceptionIfInvalid("getVehicleClassId");
        }

        return vehicleReferenceGateway.getVehicleReferenceByFilters(filters);
    }
}
