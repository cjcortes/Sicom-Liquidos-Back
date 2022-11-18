package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class DatosPropietario {
    @JsonProperty("primer_nombre")
    String primerNombre;
    @JsonProperty("segundo_nombre")
    String segundoNombre;
    @JsonProperty("primer_apellido")
    String primerApellido;
    @JsonProperty("segundo_apellido")
    String segundoApellido;
    @JsonProperty("correo")
    String correo;
    @JsonProperty("celular")
    String celular;
    @JsonProperty("registra_propietario")
    String registraPropietario;
    @JsonProperty("id_propietario")
    IdPropietario idPropietario;
}

class IdPropietario {
    @JsonProperty("id_propietario")
    int idPropietario;
}
