package com.sicom.ms.infrastructure.sql.dashboardgases.revisiontype;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getRevisionType.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_TIPO_REV",
        resultClasses = RevisionTypeData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_tipo_revision", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class RevisionTypeData {
    @Id
    @Column(name = "ID_TIPO_CERTIF")
    int certifTypeId;
    @Column(name = "NOMBRE_TIPO_CERTIF")
    String certifTypeName;
    @Column(name = "CODIGO_TIPO_CERTIF")
    String certifTypeCode;
}
