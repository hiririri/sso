package org.qjiang.sso.authentication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.qjiang.sso.authentication.domain.User;
import org.qjiang.sso.authentication.port.UserSpi;
import org.qjiang.sso.infrastructure.rest.dto.UserDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AuthenticationServiceTest {

    @Mock
    private UserSpi userSpi;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldRegisterUser() {
        // given
        var userDto = UserDto.builder()
                .username("username")
                .password("password")
                .build();

        var user = User.builder()
                .username("username")
                .password("password")
                .account("username")
                .build();

        // when
        authenticationService.register(userDto);

        // then
        verify(userSpi).save(user);
    }

    @Test
    void shouldLoginWhenPasswordMatch() {
        // given
        var user = User.builder()
                .username("username")
                .password("password")
                .account("username")
                .build();

        var userDto = UserDto.builder()
                .username("username")
                .password("password")
                .build();

        // when
        when(userSpi.findByUsername("username")).thenReturn(Optional.of(user));
        when(userSpi.verifyPassword("password", "password")).thenReturn(true);
        var result = authenticationService.login(userDto);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(user);
    }

    @Test
    void shouldNotLoginWhenPasswordNotMatch() {
        // given
        var user = User.builder()
                .username("username")
                .password("password")
                .account("username")
                .build();

        var userDto = UserDto.builder()
                .username("username")
                .password("password")
                .build();

        // when
        when(userSpi.findByUsername("username")).thenReturn(Optional.of(user));
        when(userSpi.verifyPassword("password", "password")).thenReturn(false);
        var result = authenticationService.login(userDto);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void shouldVerifyPassword() {
        // given
        var rawPassword = "password";
        var hashedPassword = "password";

        // when
        when(userSpi.verifyPassword(rawPassword, hashedPassword)).thenReturn(true);
        var result = authenticationService.verifyPassword(rawPassword, hashedPassword);

        // then
        assertThat(result).isTrue();
    }

}