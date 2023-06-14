package com.sicom.ms.infrastructure.sql.consumptions;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "consumption.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_ORDENES.INFO_ZONA_FRONTERA",
        resultClasses = ConsumptionData.class,
        parameters = {
                @StoredProcedureParameter(name = "codigo_sicom", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class ConsumptionData {

    @Id
    @Column(name = "CUPO_ASIGNADO")
    Long assignedQuota;
    @Column(name = "CUPO_FINAL")
    Long finalQuota;
    @Column(name = "CUPO_DISPONIBLE")
    Long availableQuota;
    @Column(name = "CONSUMO")
    Long consumption;
    @Column(name = "VOL_CEDIDO")
    Long cededVolume;
    @Column(name = "PORCENTAJE")
    Double percent;

}
