package com.sicom.ms.infrastructure.sql.dashboardgases.agentlocations;

import com.sicom.ms.domain.model.dashboardgases.agentlocations.AgentLocation;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgentLocationConverter extends ObjectConverter<AgentLocation, AgentLocationData> {
}
