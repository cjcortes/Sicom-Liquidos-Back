package com.sicom.ms.infrastructure.sql.dashboardgases.vehiclereference;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getVehicleReference.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_REFERENCIA_VEHIC",
        resultClasses = VehicleReferenceData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_referencia_vehiculo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "id_marca_vehiculo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "id_clase_vehiculo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "referencia1", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "referencia2", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "referencia3", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class VehicleReferenceData {
    @Id
    @Column(name = "ID_REFERENCIA_VEHICULO")
    int vehicleReferenceId;
    @Column(name = "ID_MARCA_VEHICULO")
    int vehicleBrandId;
    @Column(name = "NOMBRE_MARCA_VEHICULO")
    String vehicleBrandName;
    @Column(name = "ID_CLASE_VEHICULO")
    int vehicleClassId;
    @Column(name = "NOMBRE_CLASE_VEHICULO")
    String vehicleClassName;
    @Column(name = "REFERENCIA1")
    String reference1;
    @Column(name = "REFERENCIA2")
    String reference2;
    @Column(name = "REFERENCIA3")
    String reference3;
    @Column(name = "CODIGO_REFERENCIA_VEHICULO")
    String vehicleReferenceCode;
    @Column(name = "ID_TIPO_COMBUSTIBLE_VEHI")
    int vehicleFuelTypeId;
    @Column(name = "NOMBRE_TIPO_COMBUSTIBLE")
    String fuelTypeName;
    @Column(name = "ID_TIPO_SERVICIO_VEHI")
    int vehicleServiceTypeId;
    @Column(name = "NOMBRE_TIPO_SERVICIO_VEHI")
    String vehicleServiceTypeName;
}
