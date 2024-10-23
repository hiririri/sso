package org.qjiang.sso.infrastructure.rest.adapter;

import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.qjiang.sso.authentication.port.UserApi;
import org.qjiang.sso.infrastructure.rest.dto.UserDto;

@RequiredArgsConstructor
public class LoginAdapter {

    private UserApi userApi;

    public void register(UserDto userDTO) {
        userApi.register(userDTO);
    }

    public boolean login(UserDto userDTO) {
        if (userApi.login(userDTO).isPresent()) {
            StpUtil.login(userDTO.getUsername());
            return true;
        }
        return false;
    }
}