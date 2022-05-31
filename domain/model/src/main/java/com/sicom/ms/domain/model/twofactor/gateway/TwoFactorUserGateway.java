package com.sicom.ms.domain.model.twofactor.gateway;

import com.sicom.ms.domain.model.twofactor.TwoFactorUser;
import com.sicom.ms.domain.model.twofactor.UserStatusEnum;
import reactor.core.publisher.Mono;

public interface TwoFactorUserGateway {
    Mono<TwoFactorUser> saveOrUpdate(TwoFactorUser user);

    Mono<TwoFactorUser> findByUser(String user);

    Mono<TwoFactorUser> findBy(String user, UserStatusEnum status);
}
