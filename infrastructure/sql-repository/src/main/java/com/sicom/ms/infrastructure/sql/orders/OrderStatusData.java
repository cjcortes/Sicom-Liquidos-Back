package com.sicom.ms.infrastructure.sql.orders;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "ordersStatus.procedure",
        procedureName = "SICOM.CONSULTAR_ORDENES.OPE_PRC_ESTADO_ORDENES_X_CANT",
        resultClasses = OrderStatusData.class,
        parameters = {
                @StoredProcedureParameter(name = "codigo_sicom", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "fecha_creacion", type = Date.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "fecha_cierre", type = Date.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "codigo_producto", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "tipo_orden", type = String.class, mode = ParameterMode.IN),
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