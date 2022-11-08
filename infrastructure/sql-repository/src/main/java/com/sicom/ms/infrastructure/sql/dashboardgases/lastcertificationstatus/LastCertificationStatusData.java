package com.sicom.ms.infrastructure.sql.dashboardgases.lastcertificationstatus;

import com.sicom.ms.infrastructure.sql.dashboardgases.dualexclusives.DualExclusiveData;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getLastCertificationStatus.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_ESTADO_ULT_CERT",
        resultClasses = LastCertificationStatusData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_estado_ultima_certificacion", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class LastCertificationStatusData {
    @Id
    @Column(name = "ID_ESTADO_CERTIF")
    int certificationStatusId;
    @Column(name = "NOMBRE_ESTADO_CERTIF")
    String certificationStatusName;
    @Column(name = "CODIGO_ESTADO_CERTIF")
    String certificationStatusCode;
}
