package com.sicom.ms.infrastructure.sql.users;

import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.model.users.UsersGateway;
import com.sicom.ms.infrastructure.sql.BaseGatewayAdapter;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UsersGatewayAdapter extends BaseGatewayAdapter<User, UserData, String> implements UsersGateway {

    public UsersGatewayAdapter(ObjectConverter<User, UserData> converter) {
        super(converter);
    }

    @Override
    public Mono<User> login(LoginRequest request) {
        return Mono.just(User.builder().id("123").name("Prueba").build());
    }
}
