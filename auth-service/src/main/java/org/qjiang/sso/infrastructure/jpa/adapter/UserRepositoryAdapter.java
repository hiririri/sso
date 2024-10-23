package org.qjiang.sso.infrastructure.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.qjiang.sso.authentication.domain.User;
import org.qjiang.sso.authentication.port.UserSpi;
import org.qjiang.sso.infrastructure.jpa.entity.UserEntity;
import org.qjiang.sso.infrastructure.jpa.mapper.UserMapper;
import org.qjiang.sso.infrastructure.jpa.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserSpi {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::toUser);
    }

    @Override
    public void save(User user) {
        UserEntity entity = UserEntity.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(passwordEncoder.encode(user.getPassword()))
                .username(user.getUsername())
                .build();
        userRepository.save(entity);
    }

    @Override
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

}
