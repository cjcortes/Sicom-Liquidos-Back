package com.sicom.ms.domain.usecase.orders;

import com.sicom.ms.domain.model.orders.OrderFilters;
import com.sicom.ms.domain.usecase.validations.Rule;
import com.sicom.ms.domain.usecase.validations.Rules;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.sicom.ms.domain.usecase.validations.common.StringRulesFactory.cannotBeEmpty;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAllOrdersByFilterRules {

    public static final Rules<OrderFilters> GET_ORDERS_BY_FILTER_REQUEST_RULES = Rules.of(
            cannotBeEmpty(OrderFilters::getAuthCode, "orderFilters", "authCode"),
            cannotBeEmpty(OrderFilters::getClientCode, "orderFilters", "clientCode"),
            cannotBeEmpty(OrderFilters::getProviderPlantCode, "orderFilters", "providerPlantCode"),
            cannotBeEmpty(OrderFilters::getOrderType, "orderFilters", "orderType"),
            Rule.of("orderFilters.datesCanNotBeEmpty",
                    "you must send both dates",
                    object -> (object.getSuggestedDeliveryStartDate() != -1 && object.getSuggestedDeliveryEndDate() != -1)
                            || (object.getSuggestedDeliveryStartDate() == -1 && object.getSuggestedDeliveryEndDate() == -1))
    );
}
