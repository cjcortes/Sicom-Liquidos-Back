package com.sicom.ms.domain.model.common;

import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Map;

public interface AuthenticationGateway {

    Mono<Map<String, Object>> getClaims(Principal principal);

}
