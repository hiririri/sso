package org.qjiang.sso.authentication.service;

import lombok.RequiredArgsConstructor;
import org.qjiang.sso.authentication.domain.User;
import org.qjiang.sso.authentication.port.UserApi;
import org.qjiang.sso.authentication.port.UserSpi;
import org.qjiang.sso.infrastructure.rest.dto.UserDto;

import java.util.Optional;

@RequiredArgsConstructor
public class AuthenticationService implements UserApi {

    private UserSpi userSpi;

    @Override
    public void register(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .account(userDto.getUsername())
                .build();
        userSpi.save(user);
    }

    @Override
    public Optional<User> login(UserDto userDto) {
        return userSpi.findByUsername(userDto.getUsername())
                .filter(user -> this.verifyPassword(userDto.getPassword(), user.getPassword()))
                .map(user -> User.builder()
                        .id(user.getId())
                        .account(user.getAccount())
                        .password(user.getPassword())
                        .username(user.getUsername())
                        .build());
    }

    @Override
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return userSpi.verifyPassword(rawPassword, hashedPassword);
    }

}
