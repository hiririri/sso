package org.qjiang.sso.authentication.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {
    private Integer id;

    private String account;

    private String password;

    private String username;

    private Integer locked;

    private String avatar;

    private Date lastLoginTime;

    private String mobile;

    private String email;

    private Date createTime;

    private Date updateTime;
}
