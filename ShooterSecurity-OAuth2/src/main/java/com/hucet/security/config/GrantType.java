package com.hucet.security.config;

import lombok.Getter;

/**
 * Created by taesu on 2017-01-24.
 */
public enum GrantType {
    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token");

    @Getter
    String grantType;

    GrantType(String grantType) {
        this.grantType = grantType;
    }
}
