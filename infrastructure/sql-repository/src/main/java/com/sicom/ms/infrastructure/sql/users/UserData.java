package com.sicom.ms.infrastructure.sql.users;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(name = "UserData.Login",
        procedureName = "SEG_MOVIL_USU_IGUAL_LOGIN", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "strLogin", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "strClave", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "intSistema", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "curUsuario", type = Class.class)})
public class UserData {

    @Id
    @Column
    String id;
    @Column
    String name;
    @Column
    String token;
}
