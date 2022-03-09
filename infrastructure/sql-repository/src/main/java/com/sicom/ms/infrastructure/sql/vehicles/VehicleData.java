package com.sicom.ms.infrastructure.sql.vehicles;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "orderVehicle.procedure",
        procedureName = "SICOM.CONSULTAR_ORDENES.LISTAR_OP_DATOS_VEHICULO",
        resultClasses = VehicleData.class,
        parameters = {
                @StoredProcedureParameter(name = "codigo_autorizacion", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class VehicleData {

    @Id
    @Column(name = "TIPO_TRANSPORTE")
    String transportType;
    @Column(name = "PLACA")
    String headPlate;
    @Column(name = "REMOLQUE")
    String trailer;
    @Column(name = "CAPACIDAD")
    Integer capacity;
    @Column(name = "CONDUCTOR")
    String driver;
    @Column(name = "CEDULA")
    String driverIdentification;
    @Column(name = "COMPARTIMENTO")
    Integer compartment;

}
