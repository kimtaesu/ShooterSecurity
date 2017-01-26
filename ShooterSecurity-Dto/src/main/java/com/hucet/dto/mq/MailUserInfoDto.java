package com.hucet.dto.mq;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by taesu on 2017-01-26.
 */
@Data
@ToString
public class MailUserInfoDto implements Serializable {
    @Email
    @NotNull
    private String userEmail;
    @NotNull
    private String userName;
}
