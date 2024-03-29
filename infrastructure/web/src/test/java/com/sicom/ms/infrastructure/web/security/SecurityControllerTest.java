package com.sicom.ms.infrastructure.web.security;

import com.sicom.ms.domain.model.tokens.RefreshToken;
import com.sicom.ms.domain.model.users.AutenticacionNSRequest;
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.usecase.tokens.RefreshTokenUseCase;
import com.sicom.ms.domain.usecase.users.EncryptPasswordUseCase;
import com.sicom.ms.domain.usecase.users.AutenticacionNSUseCase;
import com.sicom.ms.domain.usecase.users.LoginUserUseCase;
import com.sicom.ms.infrastructure.web.WebTestClientFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@WebFluxTest
@ContextConfiguration(classes = {SecurityController.class})
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@TestPropertySource(properties = {"app.forti.status=false","app.two-factor.status=false"})
public class SecurityControllerTest {

    private static final String USER_DESCRIPTION = "Codigo SICOM Cliente";
    private static final String PASSWORD_DESCRIPTION = "Contraseña del SICOM Cliente";

    private static final FieldDescriptor[] LOGIN_REQUEST_DESCRIPTOR = new FieldDescriptor[]{
            fieldWithPath("user")
                    .type(JsonFieldType.STRING)
                    .description(USER_DESCRIPTION),
            fieldWithPath("password")
                    .type(JsonFieldType.STRING)
                    .description(PASSWORD_DESCRIPTION)
    };

    private static final String CODE_DESCRIPTION = "Código del usuario";
    private static final String NAME_DESCRIPTION = "Nombre del usuario";
    private static final String USER_STATE_DESCRIPTION = "Estado del usuario";
    private static final String SICOM_AGENT_DESCRIPTION = "Agente SCOM";
    private static final String AGENT_STATE_DESCRIPTION = "Estado del Agente SICOM";
    private static final String AGENT_TYPE_DESCRIPTION = "Tipo de Agente";
    private static final String PROFILE_DESCRIPTION = "Perfil del usuario";
    private static final String TOKEN_DESCRIPTION = "Token de sesión";
    private static final String TWO_FACTOR_AUTH = "estado de factor doble autenticacion";
    private static final String RESULT_AUTH = "resultado de la autenticacion";

    private static final FieldDescriptor[] USER_DESCRIPTOR = new FieldDescriptor[]{
            fieldWithPath("code")
                    .type(JsonFieldType.NUMBER)
                    .description(CODE_DESCRIPTION),
            fieldWithPath("user")
                    .type(JsonFieldType.STRING)
                    .description(USER_DESCRIPTION),
            fieldWithPath("name")
                    .type(JsonFieldType.STRING)
                    .description(NAME_DESCRIPTION),
            fieldWithPath("userState")
                    .type(JsonFieldType.STRING)
                    .description(USER_STATE_DESCRIPTION),
            fieldWithPath("sicomAgent")
                    .type(JsonFieldType.STRING)
                    .description(SICOM_AGENT_DESCRIPTION),
            fieldWithPath("agentSate")
                    .type(JsonFieldType.STRING)
                    .description(AGENT_STATE_DESCRIPTION),
            fieldWithPath("agentType")
                    .type(JsonFieldType.STRING)
                    .description(AGENT_TYPE_DESCRIPTION),
            fieldWithPath("profile")
                    .type(JsonFieldType.STRING)
                    .description(PROFILE_DESCRIPTION),
            fieldWithPath("token")
                    .type(JsonFieldType.STRING)
                    .description(TOKEN_DESCRIPTION),
            fieldWithPath("twoFactorAuth")
                    .type(JsonFieldType.BOOLEAN)
                    .description(TWO_FACTOR_AUTH),
            fieldWithPath("resultAuth")
                    .type(JsonFieldType.BOOLEAN)
                    .description(RESULT_AUTH)
    };

    @MockBean
    private LoginUserUseCase loginUserUseCase;

    @MockBean
    private RefreshTokenUseCase refreshTokenUseCase;

    @MockBean
    private EncryptPasswordUseCase encryptPasswordUseCase;

    @MockBean
    private AutenticacionNSUseCase autenticacionNSUseCase;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp(ApplicationContext applicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClientFactory.create(applicationContext, restDocumentation);
    }

    @Test
    void loginShouldReturnUserFromUseCase() {
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
                .twoFactorAuth(false)
                .resultAuth(false)
                .build();

        var request = LoginRequest.builder()
                .user("123")
                .password("password")
                .build();

        when(loginUserUseCase.login(request))
                .thenReturn(Mono.just(user));

        webTestClient.post()
                .uri("/api/security/login")
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class)
                .isEqualTo(user)
                .consumeWith(document(
                        "login",
                        requestFields(LOGIN_REQUEST_DESCRIPTOR),
                        responseFields(USER_DESCRIPTOR)
                ));

        verify(loginUserUseCase).login(request);
    }

    @Test
    void refreshTokenShouldReturnRefreshTokenFromUseCase() {
        var refreshToken = RefreshToken.builder()
                .token("newToken")
                .build();

        var request = RefreshToken.builder()
                .token("oldToken")
                .build();

        when(refreshTokenUseCase.refresh(request))
                .thenReturn(Mono.just(refreshToken));

        webTestClient.post()
                .uri("/api/security/refresh-token")
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(RefreshToken.class)
                .isEqualTo(refreshToken)
                .consumeWith(document(
                        "refresh-token",
                        requestFields(fieldWithPath("token")
                                .type(JsonFieldType.STRING)
                                .description(TOKEN_DESCRIPTION)),
                        responseFields(fieldWithPath("token")
                                .type(JsonFieldType.STRING)
                                .description(TOKEN_DESCRIPTION))
                ));

        verify(refreshTokenUseCase).refresh(request);
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
                .twoFactorAuth(false)
                .resultAuth(false)
                .build();

        var code = "123456";


        when(autenticacionNSUseCase.loginTwoFactor(user, code))
                .thenReturn(Mono.just(user));

        webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/api/security/login-two-factor")
                .queryParam("code",code).build())
                .bodyValue(user)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class)
                .isEqualTo(user);


        verify(autenticacionNSUseCase).loginTwoFactor(user, code);
    }


    @Test
    void autenticacionns() {
        var autenticacionNSRequest = AutenticacionNSRequest.builder()
                .credenciales("1234")
                .build();

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
                .twoFactorAuth(false)
                .resultAuth(false)
                .build();



        when(autenticacionNSUseCase.login(autenticacionNSRequest, false))
                .thenReturn(Mono.just(user));

        webTestClient.post()
                .uri("/api/security/loginns")
                .bodyValue(autenticacionNSRequest)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(User.class)
                .isEqualTo(user);


        verify(autenticacionNSUseCase).login(autenticacionNSRequest, false);
    }

}
