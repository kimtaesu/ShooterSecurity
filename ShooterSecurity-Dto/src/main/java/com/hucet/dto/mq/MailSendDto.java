package com.hucet.dto.mq;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by taesu on 2017-01-26.
 */
@Data
@ToString
public class MailSendDto implements Serializable {
    String to;
}
