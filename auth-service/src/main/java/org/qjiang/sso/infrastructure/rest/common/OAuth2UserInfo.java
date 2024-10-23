package org.qjiang.sso.infrastructure.rest.common;

import lombok.Data;

@Data
public class OAuth2UserInfo {

    private String account;

    private String username;

    private String userId;

    private String email;

    private String mobile;

    private String sourceType;

    private String createTime;

    private String birthday;

}
