package com.sicom.ms.infrastructure.sql.dashboard;

import com.sicom.ms.domain.model.dashboard.DashboardFilters;
import com.sicom.ms.domain.model.dashboard.DashboardGateway;
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
public class DashboardGatewayAdapter extends BaseGatewayAdapter<DashboardTotal, DashboardTotalData, Integer> implements DashboardGateway {
    private final EntityManager entityManager;

    public DashboardGatewayAdapter(EntityManager entityManager, ObjectConverter<DashboardTotal, DashboardTotalData> converter) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<DashboardTotal> getTotals(DashboardFilters dashboardFilters) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("dashboardTotals.procedure");

        storedProcedureQuery.setParameter("p_vrc_codigo_sicom", dashboardFilters.getSicomAgent());

        if(dashboardFilters.getStartDate() != -1) {
            storedProcedureQuery.setParameter("p_fecha_inicio", new Date(dashboardFilters.getStartDate()));
        }

        if(dashboardFilters.getEndDate() != -1) {
            storedProcedureQuery.setParameter("p_fecha_fin", new Date(dashboardFilters.getEndDate()));
        }

        if(dashboardFilters.getProduct() != -1) {
            storedProcedureQuery.setParameter("p_int_prod", dashboardFilters.getProduct());
        }

        if(dashboardFilters.getOrderType() != "-1") {
            storedProcedureQuery.setParameter("P_int_tipo_orden", dashboardFilters.getOrderType());
        }

        List<DashboardTotalData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}
