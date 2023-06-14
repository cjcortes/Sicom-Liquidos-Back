package com.sicom.ms.domain.model.dashboardgases.registerindividualrevision;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicom.ms.domain.model.dashboardgases.confirmequipment.ConfirmEquipment;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class VehicleRevision {
    @JsonProperty("id_Vehiculo_BPM")
    int idVehiculoBPM;
    int idTipoCombustible;
    List<ConfirmEquipment> cilindrosVehiculo;
    List<ConfirmEquipment> reguladoresVehiculo;
}
