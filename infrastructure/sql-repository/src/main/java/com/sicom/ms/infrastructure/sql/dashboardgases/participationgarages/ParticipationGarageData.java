package com.sicom.ms.infrastructure.sql.dashboardgases.participationgarages;

import com.sicom.ms.infrastructure.sql.dashboardgases.totalcertifiers.TotalCertifierData;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getParticipationGarage.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_GASES.PARTICIP_TALLERES_CERTI",
        resultClasses = ParticipationGarageData.class,
        parameters = {
                @StoredProcedureParameter(name = "cod_sicom_certificador", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class ParticipationGarageData {

        @Id
        @Column(name = "NOMBRE")
        String name;
        @Column(name = "VALOR")
        String value;
        @Column(name = "VALOR_NUM")
        int numericValue;
}
