package com.sicom.ms.infrastructure.sql.dashboardgases.equipmentbrand;

import com.sicom.ms.domain.model.dashboardgases.equipmentbrand.EquipmentBrand;
import com.sicom.ms.domain.model.dashboardgases.equipmentbrand.EquipmentBrandFilters;
import com.sicom.ms.domain.model.dashboardgases.equipmentbrand.EquipmentBrandGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.persistence.EntityManager;

@Repository
public class EquipmentBrandGatewayAdapter extends BaseGatewayAdapter<EquipmentBrand, EquipmentBrandData, Integer> implements EquipmentBrandGateway {

    private final EntityManager entityManager;

    public EquipmentBrandGatewayAdapter(ObjectConverter<EquipmentBrand, EquipmentBrandData> converter, EntityManager entityManager) {
        super(converter);
        this.entityManager = entityManager;
    }

    @Override
    public Flux<EquipmentBrand> getEquipmentBrandByFilters(EquipmentBrandFilters request) {
        return null;
    }
}
