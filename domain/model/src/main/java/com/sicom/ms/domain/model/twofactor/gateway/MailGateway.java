package com.sicom.ms.domain.model.twofactor.gateway;

import com.sicom.ms.domain.model.twofactor.MailRequest;
import reactor.core.publisher.Mono;

public interface MailGateway {
    Mono<String> send(MailRequest request);
}
