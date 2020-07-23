package com.sicom.ms.infrastructure.sql.orderinfo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "orderInfo.procedure",
        procedureName = "SEG_MOVIL_PKG_IDENTIFICACION.LISTAR_OP_DATOS_PEDIDO",
        resultClasses = OrderInfoData.class,
        parameters = {
                @StoredProcedureParameter(name = "p_vrc_codaut_ope", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class OrderInfoData {

    @Id
    @Column(name = "INT_CODIGO_OPE")
    int orderCode;
    @Column(name = "TIPO_ORDEN")
    String orderType;
    @Column(name = "CODIGO_AUT")
    String authorizationCode;
    @Column(name = "ESTADO")
    String state;
    @Column(name = "OBSERVACION")
    String observation;
    @Column(name = "FECHA_SUGERIDA")
    String suggestedDate;
    @Column(name = "NUMERO_FACTURA")
    String billNumber;
    @Column(name = "GUIA_TRANSPORTE")
    String transportGuide;
    @Column(name = "GUIA_PLANTA")
    String plantGuide;
    @Column(name = "VIGENCIA_GUIA")
    String guideValidity;
    @Column(name = "TIPO_TRANSPORTE")
    String transportType;
    @Column(name = "CUPO_FRONTERA")
    String borderQuota;
    @Column(name = "CUPO_NACIONAL")
    String nationalQuota;
    @Column(name = "OBSERVACION_ADICIONAL")
    String additionalObservation;

}
