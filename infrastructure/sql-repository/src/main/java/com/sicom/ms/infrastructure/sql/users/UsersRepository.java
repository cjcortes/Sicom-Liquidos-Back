package com.sicom.ms.infrastructure.sql.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<UserData, Integer> {

    @Procedure(name = "UserData.Login")
    UserData login(@Param("strLogin") String user, @Param("strClave") String password, @Param("intSistema") Integer system);
}
