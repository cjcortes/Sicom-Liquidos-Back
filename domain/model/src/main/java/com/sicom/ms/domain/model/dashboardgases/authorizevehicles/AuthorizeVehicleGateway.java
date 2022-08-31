package com.sicom.ms.domain.model.dashboardgases.authorizevehicles;

import reactor.core.publisher.Flux;

public interface AuthorizeVehicleGateway {
    Flux<AuthorizeVehicle> getAuthorizeVehicleByFilters (AuthorizeVehicleFilters request);
}
