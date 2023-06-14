package com.sicom.ms.infrastructure.sql.providerscustomers;

import com.sicom.ms.domain.model.providerscustomers.ProviderCustomer;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProviderCustomerConverter extends ObjectConverter<ProviderCustomer, ProviderCustomerData> {

}
