package com.sicom.ms.domain.model.providerscustomers;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ProviderCustomer {

    int orderCode;
    String customerType;
    String customerSubtype;
    String customerName;
    String customerSicom;
    String customerNit;
    String customerDepartment;
    String customerMunicipality;
    String customerAddress;

    String providerSicom;
    String providerName;
    String providerType;
    String providerSubtype;
    String providerSicomPlant;
    String providerNamePlant;

}
