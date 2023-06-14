package com.sicom.ms.infrastructure.sql.dashboardgases.dedicateconvertvehicle;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getDedicateConvertVehicle.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_CONVERT_DEDIC",
        resultClasses = DedicateConvertVehicleData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_convertido_dedicado", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class DedicateConvertVehicleData {
    @Id
    @Column(name = "ID_CONVERT_DEDIC")
    int dedicateConvertId;
    @Column(name = "NOMB_CONVERT_DEDIC")
    String dedicateConvertName;
    @Column(name = "COD_CONVERT_DEDIC")
    String dedicateConvertCode;
}
