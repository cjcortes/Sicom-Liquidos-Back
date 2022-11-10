package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentmodel;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getEquipmentModel.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_MODELO_EQUIPO",
        resultClasses = EquipmentModelData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_marca_equipo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "id_modelo_equipo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class EquipmentModelData {
    @Id
    @Column(name = "ID_MODELO_EQUIPO")
    int equipmentModelId;
    @Column(name = "NOMBRE_MODELO_EQUIPO")
    String equipmentModelName;
    @Column(name = "CODIGO_MODELO_EQUIPO")
    int equipmentModelCode;
    @Column(name = "ID_MARCA_EQUIPO")
    int equipmentBrandId;
}
