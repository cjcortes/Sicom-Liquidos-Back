package com.sicom.ms.infrastructure.sql.products;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "orderProducts.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_ORDENES.LISTAR_OP_DATOS_PRODUCTOS",
        resultClasses = ProductData.class,
        parameters = {
                @StoredProcedureParameter(name = "codigo_autorizacion", type = String.class, mode = ParameterMode.IN),
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
    double requestedAmount;
    @Column(name = "VOLUMEN_ACEPTADO")
    double acceptedAmount;
    @Column(name = "VOLUMEN_DESPACHADO")
    int dispatchedAmount;
    @Column(name = "IDP_PRODUCTOCOMPRA")
    int productId;

}
