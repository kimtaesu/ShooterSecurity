package com.hucet.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by taesu on 2017-01-21.
 */
public class UserDto {
    @Getter
    @Setter
    @ToString
    public abstract static class EssentialInformation {
        //사용자가 입력한 아이디
        @NotBlank
        private String principalId;
    }

    //사용자 가입 요청
    @Getter
    @Setter
    @ToString(callSuper = true)
    public static class ApplicationRequest extends EssentialInformation {
        @Email
        @NotBlank
        private String userEmail;
        @NotBlank
        private String password;
    }
}
