package com.sicom.ms.infrastructure.sql;

public interface ObjectConverter<E, D> {

    E toEntity(D data);

    D toData(E entity);

}
