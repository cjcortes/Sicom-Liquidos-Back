package com.sicom.ms.infrastructure.sql.consumptions;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "consumptionsProducts.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_ORDENES.OPE_PRC_CONSUMO_X_PROD_NAL",
        resultClasses = ConsumptionProductData.class,
        parameters = {
                @StoredProcedureParameter(name = "codigo_sicom", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "fecha_creacion", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "fecha_cierre", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "codigo_producto", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "tipo_orden", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class ConsumptionProductData {

    @Id
    @Column(name = "PRODUCTO")
    String productName;
    @Column(name = "TOTAL_CONSUMO")
    String valueStr;
    @Column(name = "TOTAL_CONSUMO_NUM")
    String value;

}
