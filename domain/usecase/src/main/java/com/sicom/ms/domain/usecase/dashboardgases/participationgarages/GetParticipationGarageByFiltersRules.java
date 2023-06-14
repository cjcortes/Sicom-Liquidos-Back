package com.sicom.ms.domain.usecase.dashboardgases.participationgarages;

import com.sicom.ms.domain.model.dashboardgases.participationgarages.ParticipationGarageFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetParticipationGarageByFiltersRules {

    public static final Rules<ParticipationGarageFilters> GET_PARTICIPATION_GARAGE_BY_FILTER_REQUEST_RULES = Rules.of(
            cannotBeEmpty(ParticipationGarageFilters::getCertifierSicomCode, "participationGarageFilters", "certifierSicomCode"),
            Rule.of("participationGarageFilters.certifierSicomCodeCanNotBeEmpty",
                    "you must send certifier sicom code",
                    object -> (!object.getCertifierSicomCode().equals("-1") || object.getCertifierSicomCode().equals(null)))
    );
}
