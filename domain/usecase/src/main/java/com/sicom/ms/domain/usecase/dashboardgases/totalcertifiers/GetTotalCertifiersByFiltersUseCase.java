package com.sicom.ms.domain.usecase.dashboardgases.totalcertifiers;

import com.sicom.ms.domain.model.dashboardgases.totalcertifiers.TotalCertifier;
import com.sicom.ms.domain.model.dashboardgases.totalcertifiers.TotalCertifierFilters;
import com.sicom.ms.domain.model.dashboardgases.totalcertifiers.TotalCertifierGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import static com.sicom.ms.domain.usecase.dashboardgases.totalcertifiers.GetTotalCertifierByFiltersRules.GET_TOTAL_CERTIFIER_BY_FILTER_REQUEST_RULES;

@RequiredArgsConstructor
public class GetTotalCertifiersByFiltersUseCase {

    private final ObjectValidator objectValidator;

    private final TotalCertifierGateway totalCertifierGateway;

    public Flux<TotalCertifier> getByFilters(TotalCertifierFilters totalCertifierFilters){
        objectValidator.validate(totalCertifierFilters, GET_TOTAL_CERTIFIER_BY_FILTER_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("getTotalCertifiers");
        return totalCertifierGateway.getTotalCertifiersByFilters(totalCertifierFilters);
    }
}
