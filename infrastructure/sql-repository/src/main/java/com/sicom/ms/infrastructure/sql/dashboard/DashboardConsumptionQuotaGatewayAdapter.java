
package com.sicom.ms.infrastructure.sql.dashboard;

import com.sicom.ms.domain.model.dashboard.*;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.List;

@Repository
public class DashboardConsumptionQuotaGatewayAdapter extends BaseGatewayAdapter<DashboardConsumptionQuota, DashboardConsumptionQuotaData, Integer> implements DashboardConsumptionQuotaGateway {
    private final EntityManager entityManager;

    public DashboardConsumptionQuotaGatewayAdapter(ObjectConverter<DashboardConsumptionQuota, DashboardConsumptionQuotaData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<DashboardConsumptionQuota> getConsumptionAndQuota(DashboardConsumptionQuotaFilters dashboardConsumptionQuotaFilters) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("dashboardConsumptionQuota.procedure");

        storedProcedureQuery.setParameter("p_vrc_codigo_sicom", dashboardConsumptionQuotaFilters.getSicomAgent());

        if(dashboardConsumptionQuotaFilters.getYear() != -1) {
            storedProcedureQuery.setParameter("AÑO", dashboardConsumptionQuotaFilters.getYear());
        }

        if(dashboardConsumptionQuotaFilters.getMonth() != -1) {
            storedProcedureQuery.setParameter("MES", dashboardConsumptionQuotaFilters.getMonth());
        }

        if(dashboardConsumptionQuotaFilters.getProduct() != -1) {
            storedProcedureQuery.setParameter("codigo_producto", dashboardConsumptionQuotaFilters.getProduct());
        }

        if(!dashboardConsumptionQuotaFilters.getOrderType().equals("-1")) {
            storedProcedureQuery.setParameter("tipo_orden", dashboardConsumptionQuotaFilters.getOrderType());
        }

        List<DashboardConsumptionQuotaData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}

