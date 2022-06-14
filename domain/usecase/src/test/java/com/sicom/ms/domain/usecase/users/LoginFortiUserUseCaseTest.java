package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.error.ApplicationErrorDetail;
import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.SecurityGateway;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.model.users.UsersGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.sicom.ms.domain.usecase.users.LoginUserRules.LOGIN_REQUEST_RULES;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginFortiUserUseCaseTest {

    @Spy
    private final ObjectValidator objectValidator = new ObjectValidator();

    @Mock
    private UsersGateway usersGateway;

    @Mock
    private SecurityGateway securityGateway;

    @InjectMocks
    private LoginUserUseCase loginUserUseCase;

    @Test
    void loginShouldThrowBadExceptionIfRequestIsInvalid() {
        var request = LoginRequest.builder().build();

        assertThatThrownBy(() -> loginUserUseCase.login(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("login bad request")
                .hasFieldOrPropertyWithValue("code", "login.error.badRequest")
                .satisfies(this::checkBadRequestDetails);

        verify(objectValidator).validate(request, LOGIN_REQUEST_RULES);
    }

    @Test
    void loginShouldReturnUserFromRepository() {

        var request = LoginRequest.builder()
                .user("user")
                .password("password")
                .build();

        var expected = User.builder().build();

        when(usersGateway.login(request))
                .thenReturn(Mono.just(expected));

        when(securityGateway.generateToken(expected))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(loginUserUseCase.login(request))
                .expectNext(expected)
                .verifyComplete();

        verify(usersGateway).login(request);
        verify(securityGateway).generateToken(expected);
    }

    private void checkBadRequestDetails(Throwable error) {
        var badRequestException = (BadRequestException) error;

        assertThat(badRequestException.getDetails())
                .extracting(ApplicationErrorDetail::getCode, ApplicationErrorDetail::getMessage)
                .contains(
                        tuple("loginRequest.userCannotBeNull",
                                "user cannot be null"),
                        tuple("loginRequest.passwordCannotBeNull",
                                "password cannot be null"));
    }
}
