package org.qjiang.sso.infrastructure.rest.adapter;

import cn.dev33.satoken.stp.StpUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.qjiang.sso.authentication.domain.User;
import org.qjiang.sso.authentication.port.UserApi;
import org.qjiang.sso.infrastructure.rest.dto.UserDto;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class LoginAdapterTest {

    @Mock
    private UserApi userApi;

    @InjectMocks
    private LoginAdapter loginAdapter;


    static {
        mockStatic(StpUtil.class);
    }

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void shouldLoginWhenPasswordMatch() {
        // given
        var userDTO = UserDto.builder()
                .username("username")
                .password("password")
                .build();
        var user = User.builder()
                .username("username")
                .password("password")
                .id(1)
                .build();
        when(userApi.login(userDTO)).thenReturn(Optional.of(user));
        when(userApi.verifyPassword("password", "password")).thenReturn(true);

        // when
        var result = loginAdapter.login(userDTO);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldNotLoginWhenPasswordNotMatch() {
        // given
        var userDTO = UserDto.builder()
                .username("username")
                .password("password")
                .build();

        // when
        when(userApi.login(userDTO)).thenReturn(Optional.empty());

        // when
        var result = loginAdapter.login(userDTO);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldNotLoginWhenUserNotFound() {
        // given
        var userDTO = UserDto.builder()
                .username("username")
                .password("password")
                .build();
        when(userApi.login(userDTO)).thenReturn(Optional.empty());

        // when
        var result = loginAdapter.login(userDTO);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void shouldRegister() {
        // given
        var userDTO = UserDto.builder()
                .username("username")
                .password("password")
                .build();

        // when
        loginAdapter.register(userDTO);

        // then
        verify(userApi).register(userDTO);
    }

}