package com.sicom.ms.domain.model.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OPSimpleAcceptRequest {
    public String domain;
    public String userName;
    public String radNumber;
    public boolean bSuministraTransporte;
    public int idP_TipoModalidad;
    public String idP_EstadoOrdenPedido;
    public int idM_PlantaAbastecimiento;
    public String sObservacionAgentProveedor;
    public ColTransporteOPS col_TransporteOPS;
    public ColItemsPedidoAccept col_ItemsPedido;
}

class ColItemsPedidoAccept{
    public List<SCMMItemsPedidoAccept> scmm_ItemsPedido;
}

class SCMMItemsPedidoAccept{
    public double fVolumenAceptado;
    public int idProductoSolicitado;
    public boolean bRechazar;
}