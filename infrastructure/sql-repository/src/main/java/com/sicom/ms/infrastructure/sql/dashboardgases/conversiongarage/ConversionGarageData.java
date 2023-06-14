package com.sicom.ms.infrastructure.sql.dashboardgases.conversiongarage;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getConversionGarage.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_TALLER_CONVERSION",
        resultClasses = ConversionGarageData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_taller_conversion", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class ConversionGarageData {
        @Id
        @Column(name = "ID_TALLER_CONVERSION")
        int conversionGarageId;
        @Column(name = "NOMBRE_TALLER_REVISION")
        String revisionGarageName;
        @Column(name = "NIT_TALLER")
        String garageNit;
        @Column(name = "CODIGO_SICOM_TALLER")
        int garageSicomCode;
        @Column(name = "ID_MAESTRA_AGENT_TALLER")
        int garageAgentMasterId;
}
