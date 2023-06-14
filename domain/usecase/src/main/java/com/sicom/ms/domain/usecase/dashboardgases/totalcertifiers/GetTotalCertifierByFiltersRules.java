package com.sicom.ms.domain.usecase.dashboardgases.totalcertifiers;

import com.sicom.ms.domain.model.dashboardgases.totalcertifiers.TotalCertifierFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTotalCertifierByFiltersRules {

    public static final Rules<TotalCertifierFilters> GET_TOTAL_CERTIFIER_BY_FILTER_REQUEST_RULES = Rules.of(
            cannotBeEmpty(TotalCertifierFilters::getCertifierSicomCode, "totalCertifierFilters", "certifierSicomCode"),
            Rule.of("totalCertifierFilters.certifierSicomCodeCanNotBeEmpty",
                    "you must send certifier sicom code",
                    object -> (!object.getCertifierSicomCode().equals("-1") || object.getCertifierSicomCode().equals(null)))
    );
}
