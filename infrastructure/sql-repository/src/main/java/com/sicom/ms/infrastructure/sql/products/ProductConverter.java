package com.sicom.ms.infrastructure.sql.products;

import com.sicom.ms.domain.model.products.Product;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductConverter extends ObjectConverter<Product, ProductData> {
}
