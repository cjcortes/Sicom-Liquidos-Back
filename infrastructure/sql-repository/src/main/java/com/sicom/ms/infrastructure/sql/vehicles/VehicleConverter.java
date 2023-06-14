package com.sicom.ms.infrastructure.sql.vehicles;

import com.sicom.ms.domain.model.vehicles.Vehicle;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleConverter extends ObjectConverter<Vehicle, VehicleData> {

}
