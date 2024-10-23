package org.qjiang.sso.infrastructure.rest.common;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ResultCode {
    C200(200, "Success"),
    C201(201, "Created"),
    C400(400, "Bad Request"),
    C401(401, "Unauthorized"),
    C403(403, "Forbidden"),
    C404(404, "Not Found"),

    C406(406, "Parameter Error"),
    C415(415, "Media Type Not Supported"),
    C500(500, "Internal Server Error"),
    C501(501, "Database Error"),
    C502(502, "Database Insert Error"),
    C503(503, "Database Update Error"),
    C504(504, "Database Delete Error"),

    C606(606, "1.0");

    final int code;
    final String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResultCode format(String name) {
        return Arrays.stream(ResultCode.values()).filter(value -> name.equals(value.toString())).findFirst().orElse(null);
    }

    public static ResultCode formatName(int valKey) {
        return Arrays.stream(ResultCode.values()).filter(value -> valKey == value.getCode()).findFirst().orElse(null);
    }

}
