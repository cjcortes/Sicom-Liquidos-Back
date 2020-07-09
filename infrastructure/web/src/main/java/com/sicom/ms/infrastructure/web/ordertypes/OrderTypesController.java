package com.sicom.ms.infrastructure.web.ordertypes;

import com.sicom.ms.domain.model.orders.OrderType;
import com.sicom.ms.domain.usecase.ordertypes.GetAllOrderTypesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
@RequestMapping("/api/order-types")
@RequiredArgsConstructor
public class OrderTypesController {

    private final GetAllOrderTypesUseCase getAllOrderTypesUseCase;

    @GetMapping
    public Flux<OrderType> getAll(Principal principal) {
        int userCode = -1;
        if (principal != null) {
            userCode = Integer.parseInt(principal.getName());
        }
        return getAllOrderTypesUseCase.getAll(userCode);
    }

}
