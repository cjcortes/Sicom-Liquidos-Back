package com.sicom.ms.infrastructure.sql.dashboardgases.totalcertifiers;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getTotalCertifier.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_GASES.TOTALES_PERF_CERT",
        resultClasses = TotalCertifierData.class,
        parameters = {
                @StoredProcedureParameter(name = "cod_sicom_certificador", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class TotalCertifierData {

        @Id
        @Column(name = "NOMBRE")
        String name;
        @Column(name = "VALOR")
        String value;
        @Column(name = "VALOR_NUM")
        int numericValue;
}
