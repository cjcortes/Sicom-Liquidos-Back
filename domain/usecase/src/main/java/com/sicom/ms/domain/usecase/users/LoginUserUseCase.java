package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.forti.FortiGateway;
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.SecurityGateway;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.model.users.UsersGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.usecase.users.LoginUserRules.LOGIN_REQUEST_RULES;

@RequiredArgsConstructor
public class LoginUserUseCase {
    private final ObjectValidator objectValidator;
    private final UsersGateway usersGateway;
    private final SecurityGateway securityGateway;
    private final FortiGateway fortiGateway;

    public Mono<User> login(LoginRequest request, boolean fortiStatus) {
        objectValidator.validate(request, LOGIN_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("login");
        return usersGateway.login(request)
                .flatMap(securityGateway::generateToken)
                .flatMap(user -> fortiStatus ? fortiGateway.searchUser(user.getFortiUserId()).map(fortiUser ->
                        user.toBuilder()
                                .fortiUserName(fortiUser.getUserName())
                                .fortiActiveAuth(fortiUser.isAuthActive()).build()
                ): Mono.just(user));
    }
}
