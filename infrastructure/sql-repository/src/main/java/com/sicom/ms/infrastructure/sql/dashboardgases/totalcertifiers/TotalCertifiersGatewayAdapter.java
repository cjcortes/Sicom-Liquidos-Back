package com.sicom.ms.infrastructure.sql.dashboardgases.totalcertifiers;

import com.sicom.ms.domain.model.dashboardgases.totalcertifiers.TotalCertifier;
import com.sicom.ms.domain.model.dashboardgases.totalcertifiers.TotalCertifierFilters;
import com.sicom.ms.domain.model.dashboardgases.totalcertifiers.TotalCertifierGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class TotalCertifiersGatewayAdapter extends BaseGatewayAdapter<TotalCertifier, TotalCertifierData, Integer> implements TotalCertifierGateway {

    private final EntityManager entityManager;

    public TotalCertifiersGatewayAdapter(ObjectConverter<TotalCertifier, TotalCertifierData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<TotalCertifier> getTotalCertifiersByFilters(TotalCertifierFilters request) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getTotalCertifier.procedure");

        storedProcedureQuery.setParameter("cod_sicom_certificador", request.getCertifierSicomCode());

        List<TotalCertifierData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
