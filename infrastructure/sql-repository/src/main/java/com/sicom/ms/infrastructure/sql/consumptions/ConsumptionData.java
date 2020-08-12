package com.sicom.ms.infrastructure.sql.consumptions;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "consumption.procedure",
        procedureName = "SEG_MOVIL_PKG_IDENTIFICACION.INFO_ZONA_FRONTERA",
        resultClasses = ConsumptionData.class,
        parameters = {
                @StoredProcedureParameter(name = "p_VRC_SICOM", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class ConsumptionData {

    @Id
    @Column(name = "ANIO")
    Integer year;
    @Column(name = "MES")
    Integer month;
    @Column(name = "CUPO_ASIGNADO")
    Long assignedQuota;
    @Column(name = "CUPO_DISPONIBLE")
    Long availableQuota;
    @Column(name = "CONSUMO")
    Long consumption;
    @Column(name = "PORCENTAJE")
    Double percent;

}
