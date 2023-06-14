package com.sicom.ms.domain.usecase.dashboardgases.totalgaragesrevisions;

import com.sicom.ms.domain.model.dashboardgases.totalgaragesrevisions.TotalGarageRevisionFilters;
import com.sicom.ms.domain.model.orders.OrderFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTotalGarageRevisionByFilterRules {

    public static final Rules<TotalGarageRevisionFilters> GET_GARAGE_REVISION_BY_FILTER_REQUEST_RULES = Rules.of(
            cannotBeEmpty(TotalGarageRevisionFilters::getGarageSicomCode, "totalGarageRevisionFilters", "garageSicomCode"),
            Rule.of("totalGarageRevisionFilters.garageSicomCodeCanNotBeEmpty",
                    "you must send garage sicom code",
                    object -> (!object.getGarageSicomCode().equals("-1") || object.getGarageSicomCode().equals(null)))
    );
}
