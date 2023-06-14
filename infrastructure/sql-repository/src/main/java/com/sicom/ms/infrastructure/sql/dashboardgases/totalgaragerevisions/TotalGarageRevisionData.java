package com.sicom.ms.infrastructure.sql.dashboardgases.totalgaragerevisions;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getTotalGarageRevision.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_GASES.TOTALES_REVISION_TALLERES",
        resultClasses = TotalGarageRevisionData.class,
        parameters = {
                @StoredProcedureParameter(name = "cod_sicom_taller", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "fecha_ini_filtro", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "fecha_fin_filtro", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class TotalGarageRevisionData {

    @Id
    @Column(name = "NOMBRE")
    String name;
    @Column(name = "VALOR")
    String value;
    @Column(name = "VALOR_NUM")
    int numericValue;
}
