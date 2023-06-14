package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentstates;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getEquipmentState.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_GASES.CONSULT_EST_EQUIP_PLANTIL",
        resultClasses = EquipmentStateData.class,
        parameters = {
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class EquipmentStateData {

        @Id
        @Column(name = "CODIGO")
        String code;
        @Column(name = "DESCRIPCION")
        String description;
}
