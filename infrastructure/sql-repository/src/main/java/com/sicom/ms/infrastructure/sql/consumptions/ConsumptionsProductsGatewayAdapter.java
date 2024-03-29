package com.sicom.ms.infrastructure.sql.consumptions;

import com.sicom.ms.domain.model.consumptions.ConsumptionProduct;
import com.sicom.ms.domain.model.consumptions.ConsumptionsProductsFilters;
import com.sicom.ms.domain.model.consumptions.ConsumptionsProductsGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import com.sicom.ms.infrastructure.sql.dashboard.DashboardTotalData;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class ConsumptionsProductsGatewayAdapter extends BaseGatewayAdapter<ConsumptionProduct, ConsumptionProductData, Integer> implements ConsumptionsProductsGateway {
    private final EntityManager entityManager;

    public ConsumptionsProductsGatewayAdapter(ObjectConverter<ConsumptionProduct, ConsumptionProductData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<ConsumptionProduct> getConsumptionsProducts(ConsumptionsProductsFilters consumptionsProductsFilters) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("consumptionsProducts.procedure");

        storedProcedureQuery.setParameter("codigo_sicom", consumptionsProductsFilters.getSicomAgent());

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if(consumptionsProductsFilters.getStartDate() != -1) {
            storedProcedureQuery.setParameter("fecha_creacion", dateFormat.format(new Date(consumptionsProductsFilters.getStartDate())));
        }

        if(consumptionsProductsFilters.getEndDate() != -1) {
            storedProcedureQuery.setParameter("fecha_cierre", dateFormat.format(new Date(consumptionsProductsFilters.getEndDate())));
        }

        if(consumptionsProductsFilters.getProduct() != -1) {
            storedProcedureQuery.setParameter("codigo_producto", consumptionsProductsFilters.getProduct());
        }

        if(!consumptionsProductsFilters.getOrderType().equals("-1")) {
            storedProcedureQuery.setParameter("tipo_orden", consumptionsProductsFilters.getOrderType());
        }

        List<ConsumptionProductData> result = storedProcedureQuery.getResultList();

        return Flux.fromIterable(result).map(this::toEntity);
    }
}