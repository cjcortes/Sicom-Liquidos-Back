package com.sicom.ms.infrastructure.sql.dashboardgases.totalconvertvehicles;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getTotalConvertVehicle.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_GASES.TOTALES_CONVERT_TALLERES",
        resultClasses = TotalConvertVehicleData.class,
        parameters = {
                @StoredProcedureParameter(name = "cod_sicom_taller", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "tipo_conversion", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class TotalConvertVehicleData {

    @Id
    @Column(name = "NOMBRE")
    String name;
    @Column(name = "VALOR")
    String value;
    @Column(name = "VALOR_NUM")
    int numericValue;
}
