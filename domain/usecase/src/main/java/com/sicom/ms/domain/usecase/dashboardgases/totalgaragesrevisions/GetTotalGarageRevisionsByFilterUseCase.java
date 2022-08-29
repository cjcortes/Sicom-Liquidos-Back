package com.sicom.ms.domain.usecase.dashboardgases.totalgaragesrevisions;

import com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions.TotalGarageRevision;
import com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions.TotalGarageRevisionFilters;
import com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions.TotalGarageRevisionsGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.dashboardgases.totalgaragesrevisions.GetTotalGarageRevisionByFilterRules.GET_GARAGE_REVISION_BY_FILTER_REQUEST_RULES;

@RequiredArgsConstructor
public class GetTotalGarageRevisionsByFilterUseCase {

    private final ObjectValidator objectValidator;
    private final TotalGarageRevisionsGateway totalConvertVehiclesGateway;

    public Flux<TotalGarageRevision> getByFilters(TotalGarageRevisionFilters totalGarageRevisionFilters){
        objectValidator.validate(totalGarageRevisionFilters, GET_GARAGE_REVISION_BY_FILTER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getTotalConvertVehicles");
        return totalConvertVehiclesGateway.getTotalGarageRevisionsByFilters(totalGarageRevisionFilters);
    }
}
