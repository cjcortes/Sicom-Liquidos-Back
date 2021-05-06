package com.sicom.ms.domain.usecase.forti;

import com.sicom.ms.domain.model.forti.FortiGateway;
import com.sicom.ms.domain.model.forti.ValidateTokenRequest;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FortiUseCase {
    private final FortiGateway fortiGateway;

    public Mono<String> validateToken(ValidateTokenRequest validateTokenRequest) {
        return fortiGateway.validateToken(validateTokenRequest);
    }
}
