package com.sicom.ms.infrastructure.sql.products;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "orderProducts.procedure",
        procedureName = "SEG_MOVIL_PKG_IDENTIFICACION.LISTAR_OP_DATOS_PRODUCTOS",
        resultClasses = ProductData.class,
        parameters = {
                @StoredProcedureParameter(name = "p_vrc_codaut_ope", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class ProductData {

    @Id
    @Column(name = "NOMBRE_PRODUCTO")
    String name;
    @Column(name = "ESTADO_DETALLE")
    String state;
    @Column(name = "VOLUMEN_SOLICITADO")
    int requestedAmount;
    @Column(name = "VOLUMEN_ACEPTADO")
    int acceptedAmount;
    @Column(name = "VOLUMEN_DESPACHADO")
    int dispatchedAmount;

}
