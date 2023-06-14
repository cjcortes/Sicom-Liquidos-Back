package com.sicom.ms.infrastructure.sql.dashboardgases.fueltypeparametric;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getFuelTypeParametric.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_TIPO_COMBUST",
        resultClasses = FuelTypeParametricData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_tipo_combustible", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class FuelTypeParametricData {
    @Id
    @Column(name = "ID_TIPO_COMBUS")
    int fuelTypeId;
    @Column(name = "NOMB_TIPO_COMBUS")
    String fuelTypeName;
    @Column(name = "COD_TIPO_COMBUS")
    String fuelTypeCode;
}
