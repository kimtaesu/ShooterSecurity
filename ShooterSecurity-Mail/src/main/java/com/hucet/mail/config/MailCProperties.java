package com.hucet.mail.config;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2017-01-26.
 */
@Component
@Data
@ConfigurationProperties(prefix = "mail", locations = {"classpath:mail-properties.yml"})
public class MailCProperties {
    @Data
    public static class Smtp {
        private Boolean auth;
        private Boolean startTlsRequired;
        private Boolean startTlsEnable;
    }

    private String host;
    private String protocol;
    private int port;
    private String email;
    private String password;
    private String defaultEncoding;
    private String debug;
    @NotNull
    private Smtp smtp;
}
