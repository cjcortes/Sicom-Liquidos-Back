package com.sicom.ms.infrastructure.sql.users;

import com.sicom.ms.domain.model.error.UnauthorizedException;
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class UsersRepository {

    private final EntityManager entityManager;

    public UsersRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public Mono<UserData> login(LoginRequest loginRequest) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("login.procedure");

        storedProcedureQuery.setParameter("strLogin", loginRequest.getUser());
        storedProcedureQuery.setParameter("intSistema", 181);
        storedProcedureQuery.setParameter("strClave", loginRequest.getPassword());

        List result = storedProcedureQuery.getResultList();

        if (result.size() > 0) {
            return Mono.just((UserData) result.get(0));
        }

        throw new UnauthorizedException("user.error.invalid", "user or password invalid");
    }
}
