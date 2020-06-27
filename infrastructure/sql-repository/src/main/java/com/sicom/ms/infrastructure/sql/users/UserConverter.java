package com.sicom.ms.infrastructure.sql.users;


import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter extends ObjectConverter<User, UserData> {

}
