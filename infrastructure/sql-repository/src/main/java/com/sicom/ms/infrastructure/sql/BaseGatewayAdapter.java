package com.sicom.ms.infrastructure.sql;

import lombok.RequiredArgsConstructor;
//import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BaseGatewayAdapter<E, D, I> {

//    private final JpaRepository<D, I> repository;
    private final ObjectConverter<E, D> converter;


//    public Flux<E> findAll() {
//        return repository.findAll()
//                .map(this::toEntity);
//    }
//
//    public Mono<E> findById(I id) {
//        return repository.findById(id)
//                .map(this::toEntity);
//    }
//
//    public Mono<E> save(E entity) {
//        return Mono.defer(() -> repository.save(toData(entity)))
//                .map(this::toEntity);
//    }
//
//    public Mono<Void> deleteById(I id) {
//        return repository.deleteById(id);
//    }

    protected E toEntity(D data) {
        return converter.toEntity(data);
    }

    protected D toData(E entity) {
        return converter.toData(entity);
    }

}
