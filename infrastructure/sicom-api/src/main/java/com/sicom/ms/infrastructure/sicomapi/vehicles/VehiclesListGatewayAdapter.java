package com.sicom.ms.infrastructure.sicomapi.vehicles;

import com.sicom.ms.domain.model.orders.OPSimple;
import com.sicom.ms.domain.model.vehicles.VehicleDetail;
import com.sicom.ms.domain.model.vehicles.VehicleOpSimple;
import com.sicom.ms.domain.model.vehicles.VehicleTypeOPS;
import com.sicom.ms.domain.model.vehicles.VehiclesListGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class VehiclesListGatewayAdapter implements VehiclesListGateway {
    @Value("${app.sicom.apibizagi.url}")
    private String baseUrl;

    @Override
    public Flux<VehicleOpSimple> getVehiclesBySicomAgentId(String agentId, boolean acceptOPS) {
        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        String uri = "WEBSERVICE/liquidos/ops/VehiculosAgente?codigoSicom="+agentId+"";

        if(acceptOPS) {
            uri = uri+"&aceptarOPS=true";
        }

        Mono<VehiclesDTO> vehiclesDTOMono = client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(VehiclesDTO.class);
        VehiclesDTO vehiclesDTO = vehiclesDTOMono.block();
        List<VehicleOpSimple> vehiclesList = new ArrayList<>();

        vehiclesDTO.buques.forEach(buque -> {
            vehiclesList.add(VehicleOpSimple.builder()
                    .idMAssociation(buque.idM_Asociacion)
                    .identification(buque.identificador)
                    .vehicleType(VehicleTypeOPS.SHIP.getValue())
                    .transportName(buque.nombreBuque)
                    .totalCapacity(Double.toString(buque.capacidad))
                    .compartments(Integer.toString(buque.numeroCompartimientos))
                    .driver("N/A")
                    .status(buque.estado)
                    .build());
        });

        vehiclesDTO.barcazas.forEach(barcaza -> {
            vehiclesList.add(VehicleOpSimple.builder()
                    .idMAssociation(barcaza.idM_Asociacion)
                    .identification(barcaza.identificador)
                    .vehicleType(VehicleTypeOPS.BARGE.getValue())
                    .transportName(barcaza.nombreTransporte)
                    .totalCapacity(barcaza.capacidad)
                    .compartments(barcaza.numeroCompartimientos)
                    .driver("N/A")
                    .status(barcaza.estadoVehiculo)
                    .build());
        });

        vehiclesDTO.carroTanques.forEach(carroTanque -> {
            vehiclesList.add(VehicleOpSimple.builder()
                    .idMAssociation(carroTanque.idM_Asociacion)
                    .identification(carroTanque.identificador)
                    .vehicleType(VehicleTypeOPS.TANK_CAR.getValue())
                    .transportName("N/A")
                    .totalCapacity(carroTanque.capacidadTotal)
                    .compartments(carroTanque.compartimientos)
                    .driver(carroTanque.nombreConductor)
                    .status(carroTanque.estadoVehiculo)
                    .build());
        });

        vehiclesDTO.tractocamiones.forEach(tractocamion -> {
            vehiclesList.add(VehicleOpSimple.builder()
                    .idMAssociation(tractocamion.idM_Asociacion)
                    .identification(tractocamion.identificador)
                    .vehicleType(VehicleTypeOPS.TRACTOR_TRUCK.getValue())
                    .transportName("N/A")
                    .totalCapacity(tractocamion.remolqueTractoCamion.capacidad)
                    .compartments(Integer.toString(tractocamion.remolqueTractoCamion.numeroCompartimientos))
                    .driver(tractocamion.nombreConductor)
                    .status(tractocamion.estadoVehiculo)
                    .build());
        });

        return Mono.just(vehiclesList).flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<VehicleDetail> getVehicle(String param) {
        var client = WebClient.builder()
                .baseUrl("http://eds.sicom.gov.co/eds/api/v1")
                .defaultHeaders(header -> header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.get()
                .uri("/detalleVehiculo/"+param)
                .exchange().block().bodyToMono(VehicleDetail.class);

    }
}
