package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentstatus;

import com.sicom.ms.infrastructure.sql.dashboardgases.equipmentmodel.EquipmentModelData;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getEquipmentStatus.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_ESTADO_EQUIPO",
        resultClasses = EquipmentStatusData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_estado_equipo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "id_tipo_equipo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class EquipmentStatusData {
    @Id
    @Column(name = "ID_ESTADO_EQUIPO")
    int equipmentStatusId;
    @Column(name = "NOMBRE_ESTADO_EQUIPO")
    String equipmentStatusName;
    @Column(name = "CODIGO_EQUIPO")
    String equipmentCode;
}
