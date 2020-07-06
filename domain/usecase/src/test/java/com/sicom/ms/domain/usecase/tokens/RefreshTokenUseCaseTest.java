package com.sicom.ms.domain.usecase.tokens;

import com.sicom.ms.domain.model.error.ApplicationErrorDetail;
import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.tokens.RefreshToken;
import com.sicom.ms.domain.model.users.SecurityGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.sicom.ms.domain.usecase.tokens.RefreshTokenRules.REFRESH_TOKEN_RULES;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RefreshTokenUseCaseTest {

    @Spy
    private final ObjectValidator objectValidator = new ObjectValidator();

    @Mock
    private SecurityGateway securityGateway;

    @InjectMocks
    private RefreshTokenUseCase refreshTokenUseCase;

    @Test
    void refreshShouldThrowBarExceptionIfRequestIsInvalid() {
        var request = RefreshToken.builder().build();

        assertThatThrownBy(() -> refreshTokenUseCase.refresh(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("refresh bad request")
                .hasFieldOrPropertyWithValue("code", "refresh.error.badRequest")
                .satisfies(this::checkBadRequestDetails);

        verify(objectValidator).validate(request, REFRESH_TOKEN_RULES);
    }

    @Test
    void refreshShouldReturnRefreshTokenFromRepository() {
        var request = RefreshToken.builder()
                .token("token")
                .build();

        var expected = RefreshToken.builder()
                .token("refreshedToken")
                .build();

        when(securityGateway.refreshToken(request))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(refreshTokenUseCase.refresh(request))
                .expectNext(expected)
                .verifyComplete();

        verify(securityGateway).refreshToken(request);
    }

    private void checkBadRequestDetails(Throwable error) {
        var badRequestException = (BadRequestException) error;

        assertThat(badRequestException.getDetails())
                .extracting(ApplicationErrorDetail::getCode, ApplicationErrorDetail::getMessage)
                .contains(
                        tuple("refreshToken.tokenCannotBeNull",
                                "token cannot be null"));
    }
}
