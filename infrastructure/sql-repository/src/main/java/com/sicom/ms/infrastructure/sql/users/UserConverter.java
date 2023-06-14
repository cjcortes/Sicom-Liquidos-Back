package com.sicom.ms.infrastructure.sql.users;


import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.infrastructure.sql.ObjectConverter;
import org.mapstruct.Mapper;
<<<<<<< HEAD
=======
import org.mapstruct.Mapping;
>>>>>>> release

@Mapper(componentModel = "spring")
public interface UserConverter extends ObjectConverter<User, UserData> {

<<<<<<< HEAD
=======
    @Mapping(target = "token", ignore = true)
    User toEntity(UserData data);
>>>>>>> release
}
