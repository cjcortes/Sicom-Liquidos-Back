package com.sicom.ms.infrastructure.sql.dashboard;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "dashboardTotals.procedure",
        procedureName = "SICOM.CONSULTAR_ORDENES.OPE_PRC_DINAMIC_NOM_X_VAL",
        resultClasses = DashboardTotalData.class,
        parameters = {
                @StoredProcedureParameter(name = "codigo_sicom", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "fecha_creacion", type = Date.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "fecha_cierre", type = Date.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "codigo_producto", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "tipo_orden", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class DashboardTotalData {

    @Id
    @Column(name = "NOMBRE")
    String name;
    @Column(name = "VALOR")
    String valueStr;
    @Column(name = "VALOR_NUM")
    String value;
}
