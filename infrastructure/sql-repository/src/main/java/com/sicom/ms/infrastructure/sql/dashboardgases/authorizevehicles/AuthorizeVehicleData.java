package com.sicom.ms.infrastructure.sql.dashboardgases.authorizevehicles;

import com.sicom.ms.infrastructure.sql.dashboardgases.totalcertifiers.TotalCertifierData;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getAuthorizeVehicle.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_GASES.VEHICULOS_PERF_CERT",
        resultClasses = AuthorizeVehicleData.class,
        parameters = {
                @StoredProcedureParameter(name = "cod_departamento", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cod_municipio", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cod_sicom_certificador", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class AuthorizeVehicleData {

        @Id
        @Column(name = "NOMBRE")
        String name;
        @Column(name = "VALOR")
        String value;
        @Column(name = "VALOR_NUM")
        int numericValue;
}
