package com.sicom.ms.infrastructure.sql.users;


import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserConverter extends ObjectConverter<User, UserData> {

    @Mapping(target = "token", ignore = true)
    User toEntity(UserData data);
}
