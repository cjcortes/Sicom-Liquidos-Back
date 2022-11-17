package com.sicom.ms.domain.model.dashboardgases.equipment;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class EquipmentRequest {
    public String codigoSicomAgente;
    public int idVehiculo;
    public List<Cilindro> cilindros;
    public List<Regulador> reguladores;

}

class Cilindro{
    public String serial;
    public String marca;
    public String modelo;
    public int capacidad;
    public String fechaProximaPH;
    public String fechaFabricacion;
    public String fechaVencimiento;
    public String estadoCilindro;
}

class Regulador{
    public String serial;
    public String marca;
    public String modelo;
    public String fechaVencimiento;
    public String estadoRegulador;
}
