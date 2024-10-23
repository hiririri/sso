package org.qjiang.sso.authentication.port;

import org.qjiang.sso.authentication.domain.User;
import org.qjiang.sso.infrastructure.rest.dto.UserDto;

import java.util.Optional;

public interface UserApi {
    void register(UserDto userDto);

    Optional<User> login(UserDto username);

    boolean verifyPassword(String rawPassword, String hashedPassword);
}
