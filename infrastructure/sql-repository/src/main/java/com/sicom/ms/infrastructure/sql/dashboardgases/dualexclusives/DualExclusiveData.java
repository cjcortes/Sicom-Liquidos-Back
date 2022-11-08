package com.sicom.ms.infrastructure.sql.dashboardgases.dualexclusives;

import com.sicom.ms.infrastructure.sql.dashboardgases.dedicateconvertvehicle.DedicateConvertVehicleData;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getDualExclusive.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_DUAL_EXCLUS",
        resultClasses = DualExclusiveData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_dual_exclusivo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class DualExclusiveData {
    @Id
    @Column(name = "ID_DUAL_EXCLUSIVO")
    int dualExclusiveId;
    @Column(name = "NOMBRE_DUAL_EXCLUSIVO")
    String dualExclusiveName;
    @Column(name = "CODIGO_DUAL_EXCLUSIVO")
    String dualExclusiveCode;
}
