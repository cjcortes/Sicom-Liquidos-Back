package com.sicom.ms.infrastructure.web.vehicles;

import com.sicom.ms.domain.model.common.AuthenticationGateway;
import com.sicom.ms.domain.model.vehicles.Vehicles;
import com.sicom.ms.domain.usecase.vehicles.GetVehiclesBySicomAgentIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

import static com.sicom.ms.domain.model.common.Constants.SICOM_AGENT;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehiclesController {
    private final AuthenticationGateway authenticationGateway;

    private final GetVehiclesBySicomAgentIdUseCase getVehiclesBySicomAgentIdUseCase;

    @GetMapping(value = "/get-vehicles")
    public Flux<Vehicles> get(Principal principal) {

        return authenticationGateway.getClaims(principal)
                .map(claims -> (String) claims.get(SICOM_AGENT))
                .flatMapMany(getVehiclesBySicomAgentIdUseCase::get);
    }
}