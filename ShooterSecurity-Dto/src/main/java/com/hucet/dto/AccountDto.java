package com.hucet.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by taesu on 2017-01-21.
 */
public class AccountDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ApplicationRequest {

        @NotEmpty
        private String userName;
        @Email
        @NotEmpty
        private String userEmail;
        @NotEmpty
        private String password;
    }
}
