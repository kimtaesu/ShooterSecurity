package com.hucet.service;

import com.hucet.dto.AccountDto;
import com.hucet.dto.mq.MailUserInfoDto;
import com.hucet.properties.rabbitmq.MailBindingProperties;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    void notifyEmailCert(AccountDto.ApplicationRequest dto);

    @Service
    @Transactional
    @Slf4j
    class Impl implements EmailService {
        @Autowired
        MailBindingProperties mailBindingProperties;

        @Autowired
        private RabbitTemplate rabbitTemplate;

        @Autowired
        ModelMapper mapper;
        @Override
        public void notifyEmailCert(AccountDto.ApplicationRequest dto) {
            MailUserInfoDto mailUserInfoDto = mapper.map(dto, MailUserInfoDto.class);
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
