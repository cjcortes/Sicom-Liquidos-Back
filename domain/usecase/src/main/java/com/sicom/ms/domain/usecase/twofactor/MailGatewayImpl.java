package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.di.Injectable;
import com.sicom.ms.domain.model.twofactor.MailRequest;
import com.sicom.ms.domain.model.twofactor.gateway.MailGateway;
import reactor.core.publisher.Mono;

//ToDo remover cuando se construya la implementacion api envio mail
@Deprecated
@Injectable
public class MailGatewayImpl implements MailGateway {

    @Override
    public Mono<String> send(MailRequest request) {
        return Mono.just("OK");
    }
}
