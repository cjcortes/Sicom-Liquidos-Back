package com.sicom.ms.infrastructure.sql.dashboardgases.agentlocations;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getAgentLocation.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_GASES.AGENT_ACT_MAPA",
        resultClasses = AgentLocationData.class,
        parameters = {
                @StoredProcedureParameter(name = "cod_departamento", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cod_municipio", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "tipo_gcv", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class AgentLocationData {

    @Id
    @Column(name = "ID_AGENTE")
    int agentId;
    @Column(name = "TIPO_COMBUSTIBLE")
    String fuelType;
    @Column(name = "ULTIMA_FECHA_REG_TARIFA")
    Date lastDateRegRate;
    @Column(name = "ULTIMA_TARIFA")
    int lastRate;
    @Column(name = "CODIGO_SICOM_AGENTE")
    int codeSicomAgent;
    @Column(name = "CODIGO_SUBTIPO_AGENTE")
    String codeSubtypeAgent;
    @Column(name = "TIPO_CONVERSION")
    String conversionType;
    @Column(name = "PRODUCTOS_COMERCIALIZA")
    String comercializeProducts;
    @Column(name = "NOMBRE_COMERCIAL_AGENTE")
    String commercialNameAgent;
    @Column(name = "COORDENADA_X")
    String xCoordinate;
    @Column(name = "COORDENADA_Y")
    String yCoordinate;
    @Column(name = "DEPARTAMENTO")
    String department;
    @Column(name = "MUNICIPIO")
    String city;
    @Column(name = "TIPO_DIRECCION")
    String addressType;
    @Column(name = "DIRECCION")
    String address;
    @Column(name = "DIRECCION_COMPLEMENTARIA")
    String complementaryAddress;
    @Column(name = "TELEFONO")
    String phone;
}
