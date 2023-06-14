package com.sicom.ms.infrastructure.web.ordertypes;

import com.sicom.ms.domain.usecase.orders.GetOrderModalityTypeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/orders-modality-type")
@RequiredArgsConstructor
public class OrderModalityType {
    private final GetOrderModalityTypeUseCase getOrderModalityType;

    @GetMapping()
    public Flux<com.sicom.ms.domain.model.orders.OrderModalityType> getOrderModalityType() {
        return getOrderModalityType.getOrderModalityType();
    }
}
