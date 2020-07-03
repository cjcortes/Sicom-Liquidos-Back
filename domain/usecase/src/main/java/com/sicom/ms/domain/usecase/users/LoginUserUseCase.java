package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.error.UnauthorizedException;
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.SecurityGateway;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.model.users.UsersGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.sicom.ms.domain.model.common.StringOperations.isEmpty;
import static com.sicom.ms.domain.usecase.users.LoginUserRules.LOGIN_REQUEST_RULES;

@RequiredArgsConstructor
public class LoginUserUseCase {

    private final ObjectValidator objectValidator;
    private final UsersGateway usersGateway;
    private final SecurityGateway securityGateway;

    public Mono<User> login(LoginRequest request) {
        objectValidator.validate(request, LOGIN_REQUEST_RULES)
                .throwBadRequestExceptionIfInvalid("login");
        return usersGateway.login(request).map(this::validateUser).flatMap(securityGateway::generateToken);
    }

    private User validateUser(User user) {
        if (isEmpty(user.getName())) {
            throw new UnauthorizedException("user.error.invalid", "user or password invalid");
        }
        return user;
    }
}
