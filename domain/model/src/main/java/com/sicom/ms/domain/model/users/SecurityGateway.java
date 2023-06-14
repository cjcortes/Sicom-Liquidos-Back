package com.sicom.ms.domain.model.users;

import com.sicom.ms.domain.model.tokens.RefreshToken;
import reactor.core.publisher.Mono;

public interface SecurityGateway {
    Mono<User> generateToken(User user);
<<<<<<< HEAD

=======
>>>>>>> release
    Mono<RefreshToken> refreshToken(RefreshToken refreshToken);
}
