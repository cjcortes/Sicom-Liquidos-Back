package com.sicom.ms.domain.model.dashboardgases.confirmequipment;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ConfirmEquipment {
    int idVehiculoBPM;
    int idEquipo;
    int accionEquipo;
    int idTipoEquipo;
    String serialEquipo;
    int idMarcaEquipo;
    int idModeloEquipo;
    int idTipoCilindro;
    int idEstadoEquipo;
    int idTipoCombustible;
    int capacidadEquipo;
    int idUnidadMedida;
    int intervaloRevision;
    String fechaFabricacion;
    String fechaVencimiento;
    String fechaProximaRevQuinquenal;
    String estaLibre;
    String placa;
    FotoRotulo fotoRotulo;
    ActaResultadoPruebas actaResultadoPruebas;
}

@Value
class FotoRotulo {
    String fileName;
    String fileData;
}

@Value
class ActaResultadoPruebas {
    String fileName;
    String fileData;
}