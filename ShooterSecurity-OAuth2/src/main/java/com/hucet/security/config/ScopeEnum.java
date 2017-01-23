package com.hucet.security.config;

import lombok.Getter;

/**
 * Created by taesu on 2017-01-24.
 */
public enum ScopeEnum {
    TEST("test");

    @Getter
    String scope;

    ScopeEnum(String scope) {
        this.scope = scope;
    }
}
