package com.sicom.ms.infrastructure.sql.dashboardgases.servicetype;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getServiceType.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_TIPO_SERV",
        resultClasses = ServiceTypeData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_tipo_servicio_vehiculo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class ServiceTypeData {
    @Id
    @Column(name = "ID_TIPO_SERV")
    int serviceTypeId;
    @Column(name = "NOMBRE_TIPO_SERV")
    String serviceTypeName;
    @Column(name = "CODIGO_TIPO_SERV")
    String serviceTypeCode;
}
