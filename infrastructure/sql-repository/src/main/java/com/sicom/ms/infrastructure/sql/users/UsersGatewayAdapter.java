package com.sicom.ms.infrastructure.sql.users;

<<<<<<< HEAD
=======
import com.sicom.ms.domain.model.error.UnauthorizedException;
>>>>>>> release
import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.model.users.UsersGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

<<<<<<< HEAD
@Repository
public class UsersGatewayAdapter extends BaseGatewayAdapter<User, UserData, Integer> implements UsersGateway {

    private final UsersRepository usersRepository;

    public UsersGatewayAdapter(UsersRepository usersRepository, ObjectConverter<User, UserData> converter) {
        super(converter);
        this.usersRepository = usersRepository;
=======
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Repository
public class UsersGatewayAdapter extends BaseGatewayAdapter<User, UserData, Integer> implements UsersGateway {

    private final EntityManager entityManager;

    public UsersGatewayAdapter(EntityManager entityManager, ObjectConverter<User, UserData> converter) {
        super(converter);
        this.entityManager = entityManager;
>>>>>>> release
    }

    @Override
    public Mono<User> login(LoginRequest request) {
<<<<<<< HEAD
        return Mono.just(request)
                .map(loginRequest -> usersRepository.login(loginRequest.getUser(), loginRequest.getPassword(), 123))
                .map(this::toEntity);
=======

        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("login.procedure");

        storedProcedureQuery.setParameter("strLogin", request.getUser());
        storedProcedureQuery.setParameter("intSistema", 181);
        storedProcedureQuery.setParameter("strClave", request.getPassword());

        List result = storedProcedureQuery.getResultList();

        if (result.size() > 0) {
            return Mono.just(toEntity((UserData) result.get(0)));
        }

        throw new UnauthorizedException("user.error.invalid", "user or password invalid");
>>>>>>> release
    }
}
