package com.sicom.ms.infrastructure.sql.users;

import lombok.Data;

import javax.persistence.*;
<<<<<<< HEAD

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
=======
import java.io.Serializable;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "login.procedure",
        procedureName = "SEG_MOVIL_PKG_IDENTIFICACION.SEG_MOVIL_USU_IGUAL_LOGIN",
        resultClasses = UserData.class,
        parameters = {
                @StoredProcedureParameter(name = "strLogin", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "strClave", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "intSistema", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "curUsuario", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class UserData implements Serializable {

    @Id
    @Column(name = "INT_CODIGO_USR")
    int code;
    @Column(name = "VRC_LOGIN_USR")
    String user;
    @Column(name = "VRC_NOMBRE_USR")
    String name;
    @Column(name = "CHR_ESTADO_USR")
    String userState;
    @Column(name = "VRC_SICOM_AGE")
    String sicomAgent;
    @Column(name = "CHR_ESTADO_AGE")
    String agentSate;
    @Column(name = "VRC_NOMBRE_TAG")
    String agentType;
    @Column(name = "VRC_NOMBRE_PER")
    String profile;
    @Column(name = "INT_CODUSUARIO_REST")
    int fortiUserId;
>>>>>>> release
}
