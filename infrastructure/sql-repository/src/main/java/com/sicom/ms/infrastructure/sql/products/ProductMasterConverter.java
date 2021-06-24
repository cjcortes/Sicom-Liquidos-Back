package com.sicom.ms.infrastructure.sql.products;

import com.sicom.ms.domain.model.products.Product;
import com.sicom.ms.domain.model.products.ProductMaster;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductMasterConverter extends ObjectConverter<ProductMaster, ProductMasterData> {
}