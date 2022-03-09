package com.sicom.ms.infrastructure.sql.vehicles;

import com.sicom.ms.domain.model.vehicles.Vehicle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehiclesGatewayAdapterTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private StoredProcedureQuery storedProcedureQuery;

    @Mock
    private VehicleConverter vehicleConverter;

    @InjectMocks
    private VehiclesGatewayAdapter vehiclesGatewayAdapter;

    @Test
    void getByOrderIdShouldReturnVehicleFromDb() {

        var expected = Vehicle.builder().build();

        var dataExpected = Collections.singletonList(new VehicleData());

        when(entityManager.createNamedStoredProcedureQuery("orderVehicle.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);
        when(vehicleConverter.toEntity(dataExpected.get(0)))
                .thenReturn(expected);

        StepVerifier.create(vehiclesGatewayAdapter.getByOrderId("1"))
                .expectNext(expected)
                .verifyComplete();

        verify(storedProcedureQuery).setParameter("codigo_autorizacion", "1");
    }

    @Test
    void getByOrderIdShouldReturnVehicleEmptyWhenDbReturnEmpty() {

        var dataExpected = Collections.emptyList();

        when(entityManager.createNamedStoredProcedureQuery("orderVehicle.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);

        StepVerifier.create(vehiclesGatewayAdapter.getByOrderId("1"))
                .assertNext(orderInfo -> assertThat(orderInfo).isInstanceOf(Vehicle.class))
                .verifyComplete();

        verify(storedProcedureQuery).setParameter("codigo_autorizacion", "1");
        verify(vehicleConverter, times(0)).toEntity(any());
    }
}
