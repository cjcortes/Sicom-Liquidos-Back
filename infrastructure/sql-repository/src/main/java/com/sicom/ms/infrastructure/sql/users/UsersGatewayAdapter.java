package com.sicom.ms.infrastructure.sql.users;

import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.model.users.UsersGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UsersGatewayAdapter extends BaseGatewayAdapter<User, UserData, Integer> implements UsersGateway {

    private final UsersRepository usersRepository;

    public UsersGatewayAdapter(UsersRepository usersRepository, ObjectConverter<User, UserData> converter) {
        super(converter);
        this.usersRepository = usersRepository;
    }

    @Override
    public Mono<User> login(LoginRequest request) {
        return Mono.just(request)
                .map(loginRequest -> usersRepository.login(loginRequest.getUser(), loginRequest.getPassword(), 123))
                .map(this::toEntity);
    }
}
