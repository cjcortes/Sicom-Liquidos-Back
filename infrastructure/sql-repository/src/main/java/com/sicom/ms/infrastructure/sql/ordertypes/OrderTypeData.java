package com.sicom.ms.infrastructure.sql.ordertypes;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "orderTypes.procedure",
        procedureName = "SEG_MOVIL_PKG_IDENTIFICACION.SEG_MOVIL_TIPO_ORDEN",
        resultClasses = OrderTypeData.class,
        parameters = {
                @StoredProcedureParameter(name = "strUsuario", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "curTipo_Orden", type = void.class, mode = ParameterMode.REF_CURSOR)
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
