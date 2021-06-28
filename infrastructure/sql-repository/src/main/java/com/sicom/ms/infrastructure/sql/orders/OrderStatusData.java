package com.sicom.ms.infrastructure.sql.orders;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "ordersStatus.procedure",
        procedureName = "PKG_MOVIL_CONSULTA_DASH.OPE_PRC_ESTADO_ORDENES_X_CANT",
        resultClasses = OrderStatusData.class,
        parameters = {
                @StoredProcedureParameter(name = "p_vrc_codigo_sicom", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_fecha_inicio", type = Date.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_fecha_fin", type = Date.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_int_prod", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "P_int_tipo_orden", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class OrderStatusData {

    @Id
    @Column(name = "ESTADO")
    String orderStatus;
    @Column(name = "CANTIDAD_ORDENES")
    String value;
}