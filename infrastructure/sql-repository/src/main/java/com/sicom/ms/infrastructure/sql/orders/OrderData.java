package com.sicom.ms.infrastructure.sql.orders;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "ordersByFilters.procedure",
        procedureName = "DATA_SICOM.CONSULTAR_ORDENES.SEG_MOVIL_BUSCAR_ORDEN",
        resultClasses = OrderData.class,
        parameters = {
                @StoredProcedureParameter(name = "p_vrc_codaut_ope", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_vrc_sicom_age", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_chr_tipped_ope", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_vrc_sicom_agp", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_fecha_inicio", type = Date.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_fecha_fin", type = Date.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_vrc_usuario", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "p_int_estado", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)

public class OrderData {
    @Id
    @Column(name = "CODIGO_AUTORIZACION")
    String authorizationCode;
    @Column(name = "PLACA_CABEZOTE")
    String headPlate;
    @Column(name = "REMOLQUE")
    String trailer;
    @Column(name = "TIPO_TRANSPORTE")
    String transportType;
    @Column(name = "CONDUCTOR")
    String driver;
    @Column(name = "CEDULA")
    String driverIdentification;
    @Column(name = "CODIGO_TRANSPORTE")
    Integer transportCode;
    @Column(name = "FECHA_SOLICITUD")
    Date applicationDate;
    @Column(name = "FECHA_DESPACHO")
    Date dispatchDate;
    @Column(name = "TIPO_ORDEN_PEDIDO")
    String orderType;

}
