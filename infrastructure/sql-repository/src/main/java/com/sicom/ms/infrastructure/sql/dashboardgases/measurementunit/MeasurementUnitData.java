package com.sicom.ms.infrastructure.sql.dashboardgases.measurementunit;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getMeasurementUnit.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_UNIDAD_MEDIDA",
        resultClasses = MeasurementUnitData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_unidad_medida", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class MeasurementUnitData {
        @Id
        @Column(name = "ID_UNIDAD_MEDIDA")
        int measurementUnitId;
        @Column(name = "NOMBRE_UNIDAD_MEDIDA")
        String measurementUnitName;
        @Column(name = "CODIGO_UNIDAD_MEDIDA")
        String measurementUnitCode;
}
