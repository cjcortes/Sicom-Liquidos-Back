package com.sicom.ms.domain.usecase.dashboardgases.cylindertype;

import com.sicom.ms.domain.model.dashboardgases.cylindertype.CylinderType;
import com.sicom.ms.domain.model.dashboardgases.cylindertype.CylinderTypeFilters;
import com.sicom.ms.domain.model.dashboardgases.cylindertype.CylinderTypeGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetCylinderTypeByFiltersUseCase {

    private final CylinderTypeGateway cylinderTypeGateway;

    public Flux<CylinderType> getByFilters(CylinderTypeFilters filters){
        return cylinderTypeGateway.getCylinderTypeByFilters(filters);
    }
}
