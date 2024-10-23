package org.qjiang.sso.infrastructure.jpa.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.qjiang.sso.authentication.domain.User;
import org.qjiang.sso.infrastructure.jpa.entity.UserEntity;
import org.qjiang.sso.infrastructure.jpa.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UserRepositoryAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserRepositoryAdapter userRepositoryAdapter;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldFindByUsername() {
        // given
        var username = "username";

        var userEntity = UserEntity.builder()
                .username("username")
                .password("password")
                .account("username")
                .build();

        // when
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));
        userRepositoryAdapter.findByUsername(username);

        // then
        verify(userRepository).findByUsername(username);
    }

    @Test
    void shouldSaveUser() {
        // given
        var user = User.builder()
                .username("username")
                .password("password")
                .account("username")
                .build();

        var userEntity = UserEntity.builder()
                .username("username")
                .password(passwordEncoder.encode("password"))
                .account("username")
                .build();

        // when

        userRepositoryAdapter.save(user);

        // then
        verify(userRepository).save(userEntity);
    }

    @Test
    void shouldVerifyPassword() {
        // given
        var rawPassword = "password";
        var hashedPassword = "password";

        // when
        when(passwordEncoder.matches(rawPassword, hashedPassword)).thenReturn(true);
        userRepositoryAdapter.verifyPassword(rawPassword, hashedPassword);

        // then
        verify(passwordEncoder).matches(rawPassword, hashedPassword);
    }

}