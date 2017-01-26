package com.hucet.mail.service;

import com.hucet.dto.mq.MailUserInfoDto;
import com.hucet.mail.MailContentConstructor;
import com.hucet.mail.type.EmailType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.validation.Valid;

/**
 * Created by taesu on 2017-01-26.
 */
public interface MailSendService {
    @Async(value = "emailExecutor")
    void mailSend(EmailType type, MailUserInfoDto dto);

    @Service
    @Transactional
    @Slf4j
    class Impl implements MailSendService {

        @Autowired
        JavaMailSender javaMailSender;

        @Autowired
        MailContentConstructor mailContentConstructor;

        @Autowired
        private TemplateEngine templateEngine;

        @Override
        public void mailSend(EmailType type, @Valid MailUserInfoDto dto) {
            switch (type) {
                case EMAIL_CERT:
                    log.info("sent mail : {}", dto.getUserEmail(), dto.getUserName());
                    try {
                        javaMailSender.send(mailContentConstructor.createMimeMessageForEmailCertification(dto));
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        // TODO
                    }
                    break;
            }
        }
    }
}
