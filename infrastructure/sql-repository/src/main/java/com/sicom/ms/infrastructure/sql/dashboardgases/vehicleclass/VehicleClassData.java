package com.sicom.ms.infrastructure.sql.dashboardgases.vehicleclass;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getVehicleClass.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_CLASE_VEH",
        resultClasses = VehicleClassData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_clase_vehiculo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class VehicleClassData {

    @Id
    @Column(name = "ID_CLASE_VEHICULO")
    int vehicleClassId;
    @Column(name = "CLASE_VEHICULO")
    String vehicleClass;
    @Column(name = "CODIGO_CLASE_VEHICULO")
    String vehicleClassCode;
}
