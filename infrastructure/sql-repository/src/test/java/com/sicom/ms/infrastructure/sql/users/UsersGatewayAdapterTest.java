package com.sicom.ms.infrastructure.sql.users;

import com.sicom.ms.domain.model.error.UnauthorizedException;
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersGatewayAdapterTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private StoredProcedureQuery storedProcedureQuery;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private UsersGatewayAdapter usersGatewayAdapter;

    @Test
    void loginShouldReturnUserFromDb() {

        var request = LoginRequest.builder()
                .user("user")
                .password("password")
                .build();
        var expected = User.builder().build();
        var dataExpected = Collections.singletonList(new UserData());

        when(entityManager.createNamedStoredProcedureQuery("login.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);
        when(userConverter.toEntity(dataExpected.get(0)))
                .thenReturn(expected);

        StepVerifier.create(usersGatewayAdapter.login(request))
                .expectNext(expected)
                .verifyComplete();

        verify(storedProcedureQuery).setParameter("strLogin", request.getUser());
        verify(storedProcedureQuery).setParameter("intSistema", 181);
        verify(storedProcedureQuery).setParameter("strClave", request.getPassword());
    }

    @Test
    void loginShouldThrowUnauthorizedWhenDbNotReturnUser() {

        var request = LoginRequest.builder().build();
        List<UserData> dataExpected = Collections.emptyList();

        when(entityManager.createNamedStoredProcedureQuery("login.procedure"))
                .thenReturn(storedProcedureQuery);
        when(storedProcedureQuery.getResultList())
                .thenReturn(dataExpected);

        assertThatThrownBy(() -> usersGatewayAdapter.login(request))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessage("user or password invalid")
                .hasFieldOrPropertyWithValue("code", "user.error.invalid");

        verify(storedProcedureQuery).setParameter("strLogin", request.getUser());
        verify(storedProcedureQuery).setParameter("intSistema", 181);
        verify(storedProcedureQuery).setParameter("strClave", request.getPassword());
    }
}
