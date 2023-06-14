package com.sicom.ms.infrastructure.sql.dashboardgases.vehicleyearmodel;

import com.sicom.ms.infrastructure.sql.dashboardgases.vehiclebrand.VehicleBrandData;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getVehicleYearModel.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_MODELO_ANIO",
        resultClasses = VehicleYearModelData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_modelo_ano_vehiculo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class VehicleYearModelData {
    @Id
    @Column(name = "ID_MODELO_ANIO")
    int yearModelId;
    @Column(name = "MODELO_ANIO")
    String yearModel;
    @Column(name = "CODIGO_ANIO")
    int yearCode;
}
