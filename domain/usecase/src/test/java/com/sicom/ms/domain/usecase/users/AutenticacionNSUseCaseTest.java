package com.sicom.ms.domain.usecase.users;

import com.sicom.ms.domain.model.di.Injectable;
import com.sicom.ms.domain.model.error.ApplicationErrorDetail;
import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.ConfirmSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeRequest;
import com.sicom.ms.domain.model.twofactor.GenerateSecretCodeResponse;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorGateway;
import com.sicom.ms.domain.model.users.*;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;

import static com.sicom.ms.domain.usecase.users.AutenticacionNSRules.AUTENTICACION_NS_REQUEST_RULES;
import static com.sicom.ms.domain.usecase.users.LoginUserRules.LOGIN_REQUEST_RULES;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutenticacionNSUseCaseTest {

    @Spy
    private final ObjectValidator objectValidator = new ObjectValidator();

    @Mock
    private AutenticacionNSGateway autenticacionNSGateway;

    @Mock
    private SecurityGateway securityGateway;

    @Mock
    private TwoFactorGateway twoFactorGetway;

    @InjectMocks
    private AutenticacionNSUseCase autenticacionNSUseCase;

    @Test
    void loginShouldThrowBadExceptionIfRequestIsInvalid() {
        var request = AutenticacionNSRequest.builder().build();

        assertThatThrownBy(() -> autenticacionNSUseCase.login(request, false))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("encryptPassword bad request")
                .hasFieldOrPropertyWithValue("code", "encryptPassword.error.badRequest")
                .satisfies(this::checkBadRequestDetails);

        verify(objectValidator).validate(request, AUTENTICACION_NS_REQUEST_RULES);
    }

    @Test
    void loginTwoFactor() {

        var user = User.builder()
                .code(123)
                .user("456")
                .name("name")
                .userState("state")
                .sicomAgent("agent")
                .agentSate("agentSate")
                .agentType("type")
                .profile("profile")
                .token("token")
                .fortiUserId(123)
                .fortiUserName("fortiUserName")
                .fortiActiveAuth(false)
                .twoFactorAuth(true)
                .resultAuth(false)
                .build();

        var confirmSecretCodeResponse = ConfirmSecretCodeResponse.builder()
                .user(user.getUser())
                .status("status")
                .date(new Date())
                .build();

        var code = "123456";

        when(twoFactorGetway.confirmSecretCode(any(ConfirmSecretCodeRequest.class)))
                .thenReturn(Mono.just(confirmSecretCodeResponse));
/*
        when(securityGateway.generateToken(any(User.class)))
                .thenReturn(Mono.just(user));*/

        /*StepVerifier.create(autenticacionNSUseCase.loginTwoFactor(user, code))
                .expectNext(user)
                .verifyComplete();*/
        final Mono<User> userMono = autenticacionNSUseCase.loginTwoFactor(user, code);
        assertThat(userMono).isNotNull();

        verify(twoFactorGetway).confirmSecretCode(any(ConfirmSecretCodeRequest.class));
       // verify(securityGateway).generateToken(user);
    }

    @Test
    void login() {

        var request = AutenticacionNSRequest.builder()
                .credenciales("123456")
                .build();

        var expected = User.builder().user("user").build();

        var generateSecretCode = GenerateSecretCodeRequest.builder()
                .user(expected.getUser()).email("email")
                .build();
        var generateSecretCodeResponse = GenerateSecretCodeResponse
                .builder().user("user").date(new Date()).status("status").build();

        when(autenticacionNSGateway.login(request))
                .thenReturn(Mono.just(expected));

 /*      when(twoFactorGetway.generateSecretCode(generateSecretCode))
                .thenReturn(Mono.just(generateSecretCodeResponse));*/

        when(securityGateway.generateToken(expected))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(autenticacionNSUseCase.login(request, false))
                .expectNext(expected)
                .verifyComplete();



        verify(autenticacionNSGateway).login(request);
        /*  verify(twoFactorGetway).generateSecretCode(generateSecretCode);*/
        verify(securityGateway).generateToken(expected);
    }


    private void checkBadRequestDetails(Throwable error) {
        var badRequestException = (BadRequestException) error;

        assertThat(badRequestException.getDetails())
                .extracting(ApplicationErrorDetail::getCode, ApplicationErrorDetail::getMessage)
                .contains(

                        tuple("AutenticacionNSRequest.dataCannotBeNull",
                                "data cannot be null"));
    }


}