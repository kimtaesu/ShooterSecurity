package com.hucet.service;

import com.hucet.dto.mq.MailSendDto;
import com.hucet.properties.MailBindingProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2017-01-26.
 */
public interface EmailService {
    void sendEmailMessage(MailSendDto dto);

    @Service
    @Transactional
    @Slf4j
    class Impl implements EmailService {
        @Autowired
        MailBindingProperties mailBindingProperties;

        @Autowired
        private RabbitTemplate rabbitTemplate;

        @Override
        public void sendEmailMessage(MailSendDto dto) {
            rabbitTemplate.setMessageConverter(jsonMessageConverter());
            rabbitTemplate.convertAndSend(mailBindingProperties.getExchange(),
                    mailBindingProperties.getRountingKey(),
                    dto);
        }

        @Bean
        public MessageConverter jsonMessageConverter() {
            return new Jackson2JsonMessageConverter();
        }
    }
}
