package org.qjiang.sso.infrastructure.jpa.mapper;

import org.qjiang.sso.authentication.domain.User;
import org.qjiang.sso.infrastructure.jpa.entity.UserEntity;

public class UserMapper {

    public static User toUser(UserEntity entity) {
        return User.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .account(entity.getAccount())
                .build();
    }

}
