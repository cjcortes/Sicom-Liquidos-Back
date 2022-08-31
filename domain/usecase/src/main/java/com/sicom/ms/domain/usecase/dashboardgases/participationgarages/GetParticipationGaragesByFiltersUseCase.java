package com.sicom.ms.domain.usecase.dashboardgases.participationgarages;

import com.sicom.ms.domain.model.dashboardgases.participationgarages.ParticipationGarage;
import com.sicom.ms.domain.model.dashboardgases.participationgarages.ParticipationGarageFilters;
import com.sicom.ms.domain.model.dashboardgases.participationgarages.ParticipationGarageGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.dashboardgases.participationgarages.GetParticipationGarageByFiltersRules.GET_PARTICIPATION_GARAGE_BY_FILTER_REQUEST_RULES;

@RequiredArgsConstructor
public class GetParticipationGaragesByFiltersUseCase {

    private final ObjectValidator objectValidator;

    private final ParticipationGarageGateway participationGarageGateway;

    public Flux<ParticipationGarage> getByFilters(ParticipationGarageFilters participationGarageFilters){
        objectValidator.validate(participationGarageFilters, GET_PARTICIPATION_GARAGE_BY_FILTER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getParticipationGarages");
        return participationGarageGateway.getParticipationGarageByFilters(participationGarageFilters);
    }
}
