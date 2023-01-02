package com.sicom.ms.domain.model.dashboardgases.createvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class IdMVehiculo {
    @JsonProperty("id_vehiculo_BPM")
    int idVehiculoBPM;
    boolean bVehiculoNuevo;
    boolean bEsVehiculoEspecial;
    String sSerial;
    boolean bRevisionInicial;
    String sPlaca;
    String sVIN;
    @JsonProperty("idP_Marca")
    int idPMarca;
    String sChip;
    @JsonProperty("idP_ClaseVehiculo")
    int idPClaseVehiculo;
    @JsonProperty("idP_ModeloAno")
    int idPModeloAnio;
    @JsonProperty("idP_ConvertidoDedicado")
    int idPConvertidoDedicado;
    @JsonProperty("idP_TipoCombustible")
    int idPTipoCombustible;
    @JsonProperty("idP_DualoExclusivo")
    int idPDualoExclusivo;
    @JsonProperty("idP_TipoServicioVeh")
    int idPTipoServicioVeh;
    @JsonProperty("idP_ReferenciaVehiculo")
    int idPReferenciaVehiculo;
    UTarjetaPropiedad uTarjetaPropiedad;
    @JsonProperty("idM_ConversionImportacionV")
    IdMConversionImportacionV idMConversionImportacionV;
    boolean bRegistraPropietario;
    @JsonProperty("idM_Propietario")
    IdMPropietario idMPropietario;
}

@Value
class UTarjetaPropiedad {
    String fileName;
    //String fileData;
}

@Value
class IdMConversionImportacionV {
    String dFechaConversionImporta;
    @JsonProperty("idP_TallerConversion")
    int idPTallerConversion;
    String sNITTallerdeConversion;
}

@Value
class IdMPropietario {
    String sPrimerNombre;
    String sSegundoNombre;
    String sPrimerApellido;
    String sSegundoApellido;
    String sCorreo;
    String sCelular;
}
