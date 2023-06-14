package com.sicom.ms.infrastructure.sql.dashboardgases.gcvfuelprices;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getGCVFuelPrice.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_GASES.PRECIOS_COMBUS_GCV",
        resultClasses = GcvFuelPriceData.class,
        parameters = {
                @StoredProcedureParameter(name = "cod_departamento", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cod_municipio", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "tipo_combustible", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class GcvFuelPriceData {

    @Id
    @Column(name = "NOMBRE")
    String name;
    @Column(name = "VALOR")
    String value;
    @Column(name = "VALOR_NUM")
    int numericValue;
}
