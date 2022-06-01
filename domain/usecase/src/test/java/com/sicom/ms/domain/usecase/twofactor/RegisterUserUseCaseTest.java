package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.MailRequest;
import com.sicom.ms.domain.model.twofactor.RegisterUserRequest;
import com.sicom.ms.domain.model.twofactor.RegisterUserResponse;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.TwoFactorSecretCode;
import com.sicom.ms.domain.model.twofactor.TwoFactorUser;
import com.sicom.ms.domain.model.twofactor.gateway.MailGateway;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorSecretCodeGateway;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorUserGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseTest {

    @Spy
    private final ObjectValidator objectValidator = new ObjectValidator();

    @Mock
    private TwoFactorCommon twoFactorCommon;

    @Mock
    private TwoFactorUserGateway userGateway;

    @Mock
    private TwoFactorSecretCodeGateway secretCodeGateway;

    @Mock
    private MailGateway mailGateway;

    @InjectMocks
    private RegisterUserUseCase useCase;

    @Test
    void registerUserTest() {
        final var now = Date.from(Instant.now());
        final var request = RegisterUserRequest.builder().user("user").email("email").build();
        final var response = RegisterUserResponse.builder().user("user").date(now).build();
        final var twoFactorUser = TwoFactorUser.builder().user("user").uuid("uuid").date(now).build();
        final var twoFactorSecretCode = TwoFactorSecretCode.builder().user("user").code("code").secret("secret")
                .status(SecretCodeStatusEnum.VALID.name()).date(now).build();

        when(twoFactorCommon.generateCode()).thenReturn(Mono.just("code"));
        when(userGateway.saveOrUpdate(any(TwoFactorUser.class))).thenReturn(Mono.just(twoFactorUser));
        when(twoFactorCommon.buildTwoFactorSecretCode(any(String.class), any(String.class), any(String.class), any(SecretCodeStatusEnum.class))).thenReturn(Mono.just(twoFactorSecretCode));
        when(twoFactorCommon.buildTwoFactorUser(any(String.class))).thenReturn(Mono.just(twoFactorUser));
        when(secretCodeGateway.saveOrUpdate(any(TwoFactorSecretCode.class))).thenReturn(Mono.just(twoFactorSecretCode));
        when(mailGateway.send(any(MailRequest.class))).thenReturn(Mono.just("OK"));

        StepVerifier.create(useCase.registerUser(request, "subject", "body"))
                .expectNext(response)
                .verifyComplete();
    }
}