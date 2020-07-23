package com.sicom.ms.infrastructure.sql.providerscustomers;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
        name = "orderProviderCustomer.procedure",
        procedureName = "SEG_MOVIL_PKG_IDENTIFICACION.DATOS_OP_PROVEEDOR_CLIENTE",
        resultClasses = ProviderCustomerData.class,
        parameters = {
                @StoredProcedureParameter(name = "p_vrc_codaut_op", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "cur", type = void.class, mode = ParameterMode.REF_CURSOR)
        }
)
public class ProviderCustomerData {

    @Id
    @Column(name = "INT_CODIGO_OPE")
    int orderCode;
    @Column(name = "TIPO_CLIENTE")
    String customerType;
    @Column(name = "SUBTIPO_CLIENTE")
    String customerSubtype;
    @Column(name = "NOMBRE_CLIENTE")
    String customerName;
    @Column(name = "SICOM_CLIENTE")
    String customerSicom;
    @Column(name = "NIT_CLIENTE")
    String customerNit;
    @Column(name = "DEPARTAMENTO_CLIENTE")
    String customerDepartment;
    @Column(name = "MUNICIPIO_CLIENTE")
    String customerMunicipality;
    @Column(name = "DIRECCION_CLIENTE")
    String customerAddress;

    @Column(name = "SICOM_PROVEEDOR")
    String providerSicom;
    @Column(name = "NOMBRE_PROVEEDOR")
    String providerName;
    @Column(name = "TIPO_PROVEEDOR")
    String providerType;
    @Column(name = "SUBTIPO_PROVEEDOR")
    String providerSubtype;
    @Column(name = "SICOM_PLT_PROVEEDOR")
    String providerSicomPlant;
    @Column(name = "NOMBRE_PLT_PROVEEDOR")
    String providerNamePlant;

}
