package com.sicom.ms.infrastructure.sicomapi.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class VehiclesDTO{
    @JsonProperty("Buques")
    public List<BuqueDTO> buques;
    @JsonProperty("Barcazas")
    public List<BarcazaDTO> barcazas;
    @JsonProperty("CarroTanques")
    public List<CarroTanqueDTO> carroTanques;
    @JsonProperty("Tractocamiones")
    public List<TractocamionDTO> tractocamiones;
}