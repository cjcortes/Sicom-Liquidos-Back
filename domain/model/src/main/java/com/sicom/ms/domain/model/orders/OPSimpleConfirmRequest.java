package com.sicom.ms.domain.model.orders;

import java.util.List;

public class OPSimpleConfirmRequest {
    public String domain;
    public String userName;
    public String radNumber;
    public boolean bSuministraTransporte;
    public int idP_TipoModalidad;
    public int idP_EstadoOrdenPedido;
    public int idM_PlantaAbastecimiento;
    public int idM_AgenteProveedor;
    public String dFechaSugeridaEntrega;
    public String sObservacionAgenteSolicit;
    public ColItemsPedido col_ItemsPedido;
}

class SCMMItemsPedido{
    public String fVolumenSolicitado;
    public int idP_ProductoCompra;
}

class ColItemsPedido{
    public List<SCMMItemsPedido> sCMM_ItemsPedido;
}

