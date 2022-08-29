package com.sicom.ms.infrastructure.sql.dashboardgases.totalsites;

import com.sicom.ms.infrastructure.sql.dashboardgases.fueltypes.FuelTypeData;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getTotalSite.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_GASES.GET_TOTAL_AGEN",
        resultClasses = TotalSiteData.class,
        parameters = {
                @StoredProcedureParameter(name = "cod_departamento", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cod_municipio", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "tipo_conversion", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class TotalSiteData {

        @Id
        @Column(name = "NOMBRE")
        String name;
        @Column(name = "VALOR")
        String value;
        @Column(name = "VALOR_NUM")
        int numericValue;
}
