package com.sicom.ms.infrastructure.sql.dashboardgases.cylindertype;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getCylinderType.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_TIPO_CILINDRO",
        resultClasses = CylinderTypeData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_tipo_cilindro", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class CylinderTypeData {
    @Id
    @Column(name = "ID_TIPO_CILINDRO")
    int cylinderTypeId;
    @Column(name = "NOMBRE_TIPO_CILINDRO")
    String cylinderTypeName;
    @Column(name = "CODIGO_NOMBRE_CILINDRO")
    String cylinderTypeCode;
}
