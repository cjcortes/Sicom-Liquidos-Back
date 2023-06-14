package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentbrand;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getEquipmentBrand.procedure",
        procedureName = "DATA_SICOM.APP_GCV.PARAMETRICA_MARCA_EQUIPO",
        resultClasses = EquipmentBrandData.class,
        parameters = {
                @StoredProcedureParameter(name = "id_marca_equipo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "id_tipo_equipo", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class EquipmentBrandData {
    @Id
    @Column(name = "ID_MARCA_EQUIPO")
    int equipmentBrandId;
    @Column(name = "NOMBRE_MARCA_EQUIPO")
    String equipmentBrandName;
    @Column(name = "CODIGO_MARCA_EQUIPO")
    int equipmentBrandCode;
    @Column(name = "ID_TIPO_EQUIPO")
    int equipmentTypeId;
}
