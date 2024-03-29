
package com.sicom.ms.infrastructure.sql.dashboard;

        import lombok.Data;

        import javax.persistence.*;
        import java.util.Date;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "dashboardConsumptionQuota.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_ORDENES.OPE_PRC_CONSUMO_ZONA_FRONTERA",
        resultClasses = DashboardConsumptionQuotaData.class,
        parameters = {
                @StoredProcedureParameter(name = "p_vrc_codigo_sicom", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "anio", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "mes", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_int_prod", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_int_tipo_orden", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class DashboardConsumptionQuotaData {

    @Id
    @Column(name = "CUPO_ASIGNADO")
    String quotaStr;
    @Column(name = "CUPO_ASIGNADO_NUM")
    String quota;
    @Column(name = "CUPO_CONSUMIDO")
    String consumptionStr;
    @Column(name = "CONSUMIDO_NUM")
    String consumption;

}
