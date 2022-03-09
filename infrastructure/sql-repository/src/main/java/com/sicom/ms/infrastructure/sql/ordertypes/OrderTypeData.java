package com.sicom.ms.infrastructure.sql.ordertypes;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "orderTypes.procedure",
        procedureName = "SICOM.CONSULTAR_ORDENES.SEG_MOVIL_TIPO_ORDEN",
        resultClasses = OrderTypeData.class,
        parameters = {
                @StoredProcedureParameter(name = "strUsuario", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class OrderTypeData {

    @Id
    @Column(name = "INT_CODIGO_TOR")
    int code;
    @Column(name = "CHR_NOMBRE_TOR")
    String name;
    @Column(name = "VRC_DESCRI_TOR")
    String description;

}
