
package com.sicom.ms.infrastructure.sql.dashboard;

        import lombok.Data;

        import javax.persistence.*;
        import java.util.Date;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "dashboardConsumptionQuota.procedure",
        procedureName = "PKG_MOVIL_CONSULTA_DASH.OPE_PRC_CONSUMO_ZONA_FRONTERA",
        resultClasses = DashboardConsumptionQuotaData.class,
        parameters = {
                @StoredProcedureParameter(name = "p_vrc_codigo_sicom", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "AÑO", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "MES", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_int_prod", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "P_int_tipo_orden", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class DashboardConsumptionQuotaData {

    @Id
    @Column(name = "CUPO_ASIGNADO")
    String quota;
    @Column(name = "CUPO_CONSUMIDO")
    String consumptionStr;
    @Column(name = "CONSUMIDO_NUM")
    String consumption;

}
