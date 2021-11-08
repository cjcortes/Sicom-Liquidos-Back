package com.sicom.ms.domain.model.vehicles;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class Vehicles {

    public List<Buque> buques;
    public List<Barcaza> barcazas;
    public List<CarroTanque> carroTanques;
    public List<Tractocamion> tractocamiones;

}
