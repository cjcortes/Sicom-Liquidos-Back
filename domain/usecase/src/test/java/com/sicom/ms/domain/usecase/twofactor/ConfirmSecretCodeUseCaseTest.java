package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.TwoFactorSecretCode;
import com.sicom.ms.domain.model.twofactor.TwoFactorUser;
import com.sicom.ms.domain.model.twofactor.UserStatusEnum;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfirmSecretCodeUseCaseTest {

    @Spy
    private final ObjectValidator objectValidator = new ObjectValidator();

    @Mock
    private TwoFactorCommon twoFactorCommon;

    @Mock
    private TwoFactorUserGateway userGateway;

    @Mock
    private TwoFactorSecretCodeGateway secretCodeGateway;

    @InjectMocks
    private ConfirmSecretCodeUseCase useCase;

    @Test
    void confirmSecretCodeTest() {
        final var now = Date.from(Instant.now());
        final var request = ConfirmSecretCodeRequest.builder().user("user").code("code").build();
        final var response = ConfirmSecretCodeResponse.builder().user("user").status(SecretCodeStatusEnum.VALID.name()).date(now).build();
        final var twoFactorUser = TwoFactorUser.builder().user("user").uuid("uuid")
                .status(UserStatusEnum.PENDING.name()).date(now).build();
        final var twoFactorSecretCode = TwoFactorSecretCode.builder().user("user").code("code").secret("secret")
                .status(SecretCodeStatusEnum.VALID.name()).date(now).build();

        when(userGateway.findBy(any(String.class), any(UserStatusEnum.class))).thenReturn(Mono.just(twoFactorUser));
        when(twoFactorCommon.generateSecret(any(String.class), any(String.class), any(String.class))).thenReturn(Mono.just("secret"));
        when(secretCodeGateway.findBySecret(any(String.class))).thenReturn(Mono.just(twoFactorSecretCode));
        when(secretCodeGateway.saveOrUpdate(any(TwoFactorSecretCode.class))).thenReturn(Mono.just(twoFactorSecretCode));

        StepVerifier.create(useCase.confirmSecretCode(request))
                .expectNext(response)
                .verifyComplete();

        verify(userGateway).findBy("user", UserStatusEnum.ACTIVE);
        verify(twoFactorCommon).generateSecret("uuid", "user", "code");
    }
}