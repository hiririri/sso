package org.qjiang.sso.infrastructure.rest.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import org.qjiang.sso.infrastructure.rest.adapter.LoginAdapter;
import org.qjiang.sso.infrastructure.rest.common.R;
import org.qjiang.sso.infrastructure.rest.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private LoginAdapter loginAdapter;

    @RequestMapping("/isLogin")
    public R<String> isLogin() {
        boolean isLoggedIn = StpUtil.isLogin();
        return R.ok("Login status: " + (isLoggedIn ? "Logged in" : "Not logged in"));
    }

    @RequestMapping("/tokenInfo")
    public R<SaTokenInfo> tokenInfo() {
        return R.ok(StpUtil.getTokenInfo());
    }

    @PostMapping("/login")
    public R<String> login(@RequestBody UserDto userDTO) {
        boolean loginSuccess = loginAdapter.login(userDTO);
        return loginSuccess ? R.ok("Login successful") : R.fail("Login failed");
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody UserDto userDTO) {
        loginAdapter.register(userDTO);
        return R.ok("Registration successful");
    }
}