package com.sicom.ms.infrastructure.sql.dashboardgases.equipmenttype;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getEquipmentType.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_TIPO_EQUIPO",
        resultClasses = EquipmentTypeData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_tipo_equipo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class EquipmentTypeData {
        @Id
        @Column(name = "ID_TIPO_EQUIPO")
        int equipmentTypeId;
        @Column(name = "NOMBRE_TIPO_EQUIPO")
        String equipmentTypeName;
        @Column(name = "CODIGO_NOMBRE_EQUIPO")
        String equipmentNameCode;
}
