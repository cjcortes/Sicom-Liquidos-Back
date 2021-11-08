package com.sicom.ms.infrastructure.sicomapi.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sicom.ms.domain.model.vehicles.Barcaza;
import com.sicom.ms.domain.model.vehicles.Buque;
import com.sicom.ms.domain.model.vehicles.CarroTanque;
import com.sicom.ms.domain.model.vehicles.Tractocamion;

import java.util.List;

public class VehiclesDTO{
    @JsonProperty("Buques")
    public List<Buque> buques;
    @JsonProperty("Barcazas")
    public List<Barcaza> barcazas;
    @JsonProperty("CarroTanques")
    public List<CarroTanque> carroTanques;
    @JsonProperty("Tractocamiones")
    public List<Tractocamion> tractocamiones;
}