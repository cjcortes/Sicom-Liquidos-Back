package com.sicom.ms.infrastructure.sql.dashboardgases.participationgarages;

import com.sicom.ms.domain.model.dashboardgases.participationgarages.ParticipationGarage;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipationGarageConverter extends ObjectConverter<ParticipationGarage, ParticipationGarageData> {
}
