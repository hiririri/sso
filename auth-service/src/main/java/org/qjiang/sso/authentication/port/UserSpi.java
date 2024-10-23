package org.qjiang.sso.authentication.port;

import org.qjiang.sso.authentication.domain.User;

import java.util.Optional;

public interface UserSpi {

    Optional<User> findByUsername(String username);

    void save(User user);

    boolean verifyPassword(String rawPassword, String hashedPassword);
}
