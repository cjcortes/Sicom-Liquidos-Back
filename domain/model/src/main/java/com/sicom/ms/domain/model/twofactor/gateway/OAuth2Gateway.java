package com.sicom.ms.domain.model.twofactor.gateway;

import com.sicom.ms.domain.model.twofactor.RegisterOAuth2Request;
import com.sicom.ms.domain.model.twofactor.RegisterOAuth2Response;
import reactor.core.publisher.Mono;

public interface OAuth2Gateway {
    Mono<RegisterOAuth2Response> registerOAuth2(RegisterOAuth2Request request);
}
