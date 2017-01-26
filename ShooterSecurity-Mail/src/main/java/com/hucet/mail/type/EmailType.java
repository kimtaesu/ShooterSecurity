package com.hucet.mail.type;

import lombok.Getter;

/**
 * Created by taesu on 2017-01-26.
 */
public enum EmailType {
    EMAIL_CERT("EmailCertification");

    @Getter
    String templateName;

    EmailType(String s) {
        templateName = s;
    }
}
