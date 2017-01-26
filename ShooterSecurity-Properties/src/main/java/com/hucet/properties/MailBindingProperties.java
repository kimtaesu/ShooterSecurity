package com.hucet.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by taesu on 2017-01-26.
 */
@Component
@Data
@ConfigurationProperties("spring.rabbitmq.binding.mail")
@EnableConfigurationProperties
public class MailBindingProperties {
    private String queue;
    private String exchange;
    private String rountingKey;
}
