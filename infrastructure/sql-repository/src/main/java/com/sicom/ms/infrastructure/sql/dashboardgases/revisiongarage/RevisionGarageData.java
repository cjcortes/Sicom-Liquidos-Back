package com.sicom.ms.infrastructure.sql.dashboardgases.revisiongarage;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getRevisionGarage.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_TALLER_REVISION",
        resultClasses = RevisionGarageData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_taller_revision", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class RevisionGarageData {
    @Id
    @Column(name = "ID_AGENTE_TALLER")
    int garageAgentId;
    @Column(name = "ID_TALLER_WFUSER")
    int garageWfuserId;
    @Column(name = "CODIGO_SICOM_TALLER")
    int garageSicomCode;
    @Column(name = "NOMBRE_COMERCIAL_TALLER")
    String garageTradename;
    @Column(name = "SUBTIPO_AGENTE_TALLER")
    String garageAgentSubtype;
    @Column(name = "ESTADO_AGENTE_TALLER")
    String garageAgentStatus;
}
