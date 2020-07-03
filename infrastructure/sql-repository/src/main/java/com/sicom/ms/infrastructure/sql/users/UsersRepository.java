package com.sicom.ms.infrastructure.sql.users;

import com.sicom.ms.domain.model.users.LoginRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class UsersRepository {

    private final EntityManager entityManager;

    public UsersRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public UserData login(LoginRequest loginRequest) {

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SEG_MOVIL_PKG_IDENTIFICACION.SEG_MOVIL_USU_IGUAL_LOGIN");

        storedProcedureQuery.registerStoredProcedureParameter("strLogin", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("intSistema", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("strClave", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("curUsuario", Class.class, ParameterMode.REF_CURSOR);


        storedProcedureQuery.setParameter("strLogin", loginRequest.getUser());
        storedProcedureQuery.setParameter("intSistema", 181);
        storedProcedureQuery.setParameter("strClave", loginRequest.getPassword());

        storedProcedureQuery.execute();

        System.out.println(storedProcedureQuery.hasMoreResults());
        UserData result = new UserData();
        if (storedProcedureQuery.hasMoreResults()) {
            List results = storedProcedureQuery.getResultList();
            results.forEach(System.out::println);
        }

        return result;
    }
}
