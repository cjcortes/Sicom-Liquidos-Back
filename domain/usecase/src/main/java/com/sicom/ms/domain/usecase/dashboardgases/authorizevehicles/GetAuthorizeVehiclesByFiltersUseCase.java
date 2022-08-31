package com.sicom.ms.domain.usecase.dashboardgases.authorizevehicles;

import com.sicom.ms.domain.model.dashboardgases.authorizevehicles.AuthorizeVehicle;
import com.sicom.ms.domain.model.dashboardgases.authorizevehicles.AuthorizeVehicleFilters;
import com.sicom.ms.domain.model.dashboardgases.authorizevehicles.AuthorizeVehicleGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.dashboardgases.authorizevehicles.GetAuthorizeVehicleByFiltersRules.GET_AUTHORIZE_VEHICLE_BY_FILTER_REQUEST_RULES;

@RequiredArgsConstructor
public class GetAuthorizeVehiclesByFiltersUseCase {

    private final ObjectValidator objectValidator;

    private final AuthorizeVehicleGateway authorizeVehicleGateway;

    public Flux<AuthorizeVehicle> getByFilters(AuthorizeVehicleFilters authorizeVehicleFilters){
        objectValidator.validate(authorizeVehicleFilters, GET_AUTHORIZE_VEHICLE_BY_FILTER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getAuthorizeVehicles");
        return authorizeVehicleGateway.getAuthorizeVehicleByFilters(authorizeVehicleFilters);
    }
}
