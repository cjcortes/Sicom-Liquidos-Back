package com.sicom.ms.infrastructure.web.vehicles;

import com.sicom.ms.domain.model.orders.OrderDetail;
import com.sicom.ms.domain.model.vehicles.VehicleDetail;
import com.sicom.ms.domain.model.vehicles.VehicleOpSimple;
import com.sicom.ms.domain.usecase.vehicles.GetVehicleInfoUseCase;
import com.sicom.ms.domain.usecase.vehicles.GetVehiclesBySicomCodeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehiclesController {
    private final GetVehiclesBySicomCodeUseCase getVehiclesBySicomCodeUseCase;
    private final GetVehicleInfoUseCase getVehicleInfoUseCase;

    @GetMapping()
    public Flux<VehicleOpSimple> get(@RequestParam String sicomCode,
                                     @RequestParam(defaultValue = "false") boolean acceptOPS) {
        return getVehiclesBySicomCodeUseCase.get(sicomCode, acceptOPS);
    }

    //get vehivle by plate, vim, chip
    @GetMapping(value = "/{param}")
    public Mono<VehicleDetail> get(@PathVariable(value = "param") String param) {
        return getVehicleInfoUseCase.get(param);
    }
}