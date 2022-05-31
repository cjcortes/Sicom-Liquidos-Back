package com.sicom.ms.domain.model.vehicles;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VehicleDetail{
    public String sistemaConbustible;
    public String placa;
    public String vin;
    public String chip;
    public String modelo;
    public String marca;
    public String publico;
    public String capacidad;
    public String estado;
    public String motivo;
    public String fechaCertificacion;
    public String tipoRevision;
    public String taller;
    public String certificador;
    public List<DatosRegulador> datosRegulador;
    public List<DatosCilindro> datosCilindro;
    @JsonProperty("HistoricoRevisiones")
    public List<HistoricoRevisione> historicoRevisiones;
    @JsonProperty("ProxRevisionAnual")
    public String proxRevisionAnual;
}

class DatosCilindro{
    public String numCilindros;
    public String serial;
    public String marca;
    public String modelo;
    public String capacidad;
    public String fechaProximoPH;
    public String fechaFabricacion;
    public String fechaVencimiento;
}

class DatosRegulador{
    public String numCilindros;
    public String serial;
    public String marca;
    public String modelo;
    public Object fechaVencimiento;
}

class HistoricoRevisione{
    public String numRevision;
    public String fechaRevision;
    public String tipoRevision;
    public String tallerRevision;
    public Object certificadorRevision;
    public String fechaRegistro;
}

