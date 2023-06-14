package com.sicom.ms.infrastructure.sql.dashboardgases.conversiongarage;

import com.sicom.ms.domain.model.dashboardgases.conversiongarage.ConversionGarage;
import com.sicom.ms.domain.model.dashboardgases.conversiongarage.ConversionGarageFilters;
import com.sicom.ms.domain.model.dashboardgases.conversiongarage.ConversionGarageGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class ConversionGarageGatewayAdapter extends BaseGatewayAdapter<ConversionGarage, ConversionGarageData, Integer> implements ConversionGarageGateway {

    private final EntityManager entityManager;

    public ConversionGarageGatewayAdapter(ObjectConverter<ConversionGarage, ConversionGarageData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<ConversionGarage> getConversionGarageByFilters(ConversionGarageFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getConversionGarage.procedure");

        storedProcedureQuery.setParameter("id_taller_conversion", request.getConversionGarageId());

        List<ConversionGarageData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
