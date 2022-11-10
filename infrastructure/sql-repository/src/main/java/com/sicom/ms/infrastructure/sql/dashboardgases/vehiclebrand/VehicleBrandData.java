package com.sicom.ms.infrastructure.sql.dashboardgases.vehiclebrand;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getVehicleBrand.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_MARCA_VEH",
        resultClasses = VehicleBrandData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_marca_vehiculo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class VehicleBrandData {
    @Id
    @Column(name = "ID_MARCA_VEHICULO")
    int vehicleBrandId;
    @Column(name = "MARCA_VEHICULO")
    String vehicleBrand;
    @Column(name = "CODIGO_MARCA_VEHICULO")
    int vehicleBrandCode;
}
