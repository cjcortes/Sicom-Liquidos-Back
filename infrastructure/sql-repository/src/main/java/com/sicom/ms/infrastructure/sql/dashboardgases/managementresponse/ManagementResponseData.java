package com.sicom.ms.infrastructure.sql.dashboardgases.managementresponse;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getManagementResponse.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_RESPUESTA_GEST",
        resultClasses = ManagementResponseData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_respuesta_gestion", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class ManagementResponseData {
        @Id
        @Column(name = "ID_RTA_GESTION")
        int managementResponseId;
        @Column(name = "NOMBRE_RTA_GESTION")
        String managementResponseName;
        @Column(name = "CODIGO_RTA_GESTION")
        String managementResponseCode;
}
