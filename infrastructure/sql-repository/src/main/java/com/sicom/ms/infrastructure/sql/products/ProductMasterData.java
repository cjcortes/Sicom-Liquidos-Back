package com.sicom.ms.infrastructure.sql.products;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "listProducts.procedure",
        procedureName = "PKG_MOVIL_CONSULTA_DASH.OPE_PRC_CONSULTA_PRODUCTOS",
        resultClasses = ProductMasterData.class,
        parameters = {
                @StoredProcedureParameter(name = "p_vrc_codigo_sicom", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class ProductMasterData {

    @Id
    @Column(name = "INT_CODIGO_PRO")
    String productCode;
    @Column(name = "VRC_NOMBRE_PRO")
    String name;

}
