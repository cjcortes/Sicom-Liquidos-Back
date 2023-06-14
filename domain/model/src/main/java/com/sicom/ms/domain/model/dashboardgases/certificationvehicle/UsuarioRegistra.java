package com.sicom.ms.domain.model.dashboardgases.certificationvehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class UsuarioRegistra {
    @JsonProperty("user_usuario_registra")
    String userUsuarioRegistra;
    @JsonProperty("id_user_usuario_registra")
    IdUserUsuarioRegistra idUserUsuarioRegistra;
}

class IdUserUsuarioRegistra {
    @JsonProperty("id_user_usuario_registra")
    int idUserUsuarioRegistra;
}
