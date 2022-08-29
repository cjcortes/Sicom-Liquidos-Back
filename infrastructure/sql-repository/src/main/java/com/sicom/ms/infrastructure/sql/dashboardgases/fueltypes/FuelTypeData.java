package com.sicom.ms.infrastructure.sql.dashboardgases.fueltypes;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getFuelType.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_GASES.CONSULT_TIP_COMBUS",
        resultClasses = FuelTypeData.class,
        parameters = {
                @StoredProcedureParameter(name = "cod_combustible", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class FuelTypeData {

        @Id
        @Column(name = "NOMBRE_COMBUSTIBLE")
        String fuelName;
        @Column(name = "CODIGO_COMBUSTIBLE")
        String fuelCode;
}
