package com.sicom.ms.domain.model.twofactor.gateway;

import com.sicom.ms.domain.model.twofactor.MailRequest;
import com.sicom.ms.domain.model.twofactor.MailResponse;
import reactor.core.publisher.Mono;

public interface MailGateway {
    Mono<MailResponse> send(MailRequest request, String secretCode);
}
