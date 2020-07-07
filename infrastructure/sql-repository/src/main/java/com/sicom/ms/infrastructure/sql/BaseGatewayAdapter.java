package com.sicom.ms.infrastructure.sql;

import lombok.RequiredArgsConstructor;
//import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BaseGatewayAdapter<E, D, I> {

    private final ObjectConverter<E, D> converter;

    protected E toEntity(D data) {
        return converter.toEntity(data);
    }

    protected D toData(E entity) {
        return converter.toData(entity);
    }

}
