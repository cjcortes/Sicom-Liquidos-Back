package com.sicom.ms.infrastructure.sql.dashboardgases.departmentscities;

import com.sicom.ms.infrastructure.sql.dashboardgases.fueltypes.FuelTypeData;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "getDepartmentCity.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_GASES.CONSULT_DEP_MUNICIP",
        resultClasses = DepartmentCityData.class,
        parameters = {
                @StoredProcedureParameter(name = "cod_municipio", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cod_departamento", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class DepartmentCityData {

    @Id
    @Column(name = "CODIGO_MUNICIPIO")
    String cityCode;
    @Column(name = "NOMBRE_MUNICIPIO")
    String cityName;
    @Column(name = "CODIGO_DEPARTAMENTO")
    String departmentCode;
    @Column(name = "NOMBRE_DEPARTAMENTO")
    String departmentName;
}
