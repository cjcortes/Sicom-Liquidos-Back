package com.sicom.ms.domain.model.orders;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

class ScmMTransporteOP{
    public int idM_Asociacion;
    public boolean seleccionado;
}

class ColTransporteOPS{
    public List<ScmMTransporteOP> scmM_TransporteOPS;
}

class SCMMItemsPedido{
    public int fVolumenSolicitado;
    public int idP_ProductoCompra;
    public boolean bRechazar;
}

class ColItemsPedido{
    @JsonProperty("SCMM_ItemsPedido")
    public List<SCMMItemsPedido> sCMM_ItemsPedido;
}

public class OPSimpleConfirmRequest{
    public String domain;
    public String userName;
    public String radNumber;
    public boolean bSuministraTransporte;
    @JsonProperty("IdP_TipoModalidad")
    public int idP_TipoModalidad;
    @JsonProperty("IdP_EstadoOrdenPedido")
    public String idP_EstadoOrdenPedido;
    @JsonProperty("IdM_PlantaAbastecimiento")
    public int idM_PlantaAbastecimiento;
    @JsonProperty("IdM_PlantaRecibo")
    public int idM_PlantaRecibo;
    @JsonProperty("IdM_AgenteProveedor")
    public int idM_AgenteProveedor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    public LocalDateTime dFechaSugeridaEntrega;
    public String sObservacionAgenteSolicit;
    public ColTransporteOPS col_TransporteOPS;
    @JsonProperty("Col_ItemsPedido")
    public ColItemsPedido col_ItemsPedido;
}
