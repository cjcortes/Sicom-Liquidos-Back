package com.sicom.ms.infrastructure.sql.dashboard;

import com.sicom.ms.domain.model.dashboard.DashboardTotalsFilters;
import com.sicom.ms.domain.model.dashboard.DashboardTotalsGateway;
import com.sicom.ms.domain.model.dashboard.DashboardTotal;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.List;

@Repository
public class DashboardTotalsGatewayAdapter extends BaseGatewayAdapter<DashboardTotal, DashboardTotalData, Integer> implements DashboardTotalsGateway {
    private final EntityManager entityManager;

    public DashboardTotalsGatewayAdapter(EntityManager entityManager, ObjectConverter<DashboardTotal, DashboardTotalData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<DashboardTotal> getTotals(DashboardTotalsFilters dashboardTotalsFilters) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("dashboardTotals.procedure");

        storedProcedureQuery.setParameter("p_vrc_codigo_sicom", dashboardTotalsFilters.getSicomAgent());

        if(dashboardTotalsFilters.getStartDate() != -1) {
            Date startDate = new Date(dashboardTotalsFilters.getStartDate());
            storedProcedureQuery.setParameter("p_fecha_inicio", startDate);
        }

        if(dashboardTotalsFilters.getEndDate() != -1) {
            Date endDate = new Date(dashboardTotalsFilters.getEndDate());
            storedProcedureQuery.setParameter("p_fecha_fin", endDate);
        }

        if(dashboardTotalsFilters.getProduct() != -1) {
            storedProcedureQuery.setParameter("p_int_prod", dashboardTotalsFilters.getProduct());
        }

        if(!dashboardTotalsFilters.getOrderType().equals("-1")) {
            storedProcedureQuery.setParameter("P_int_tipo_orden", dashboardTotalsFilters.getOrderType());
        }

        List<DashboardTotalData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
