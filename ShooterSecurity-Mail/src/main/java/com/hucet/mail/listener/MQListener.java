package com.hucet.mail.listener;

import com.hucet.dto.mq.MailUserInfoDto;
import com.hucet.mail.service.MailSendService;
import com.hucet.mail.type.EmailType;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;


public interface MQListener {
    void onReceiveredMailForCert(MailUserInfoDto mailUserInfoDto);

    @Service
    @Slf4j
    class MQListenerImpl implements MQListener {

        @Autowired
        MailSendService mailSendService;

        @Override
        public void onReceiveredMailForCert(MailUserInfoDto mailUserInfoDto) {
            log.info(mailUserInfoDto.toString());
            mailSendService.mailSend(EmailType.EMAIL_CERT, mailUserInfoDto);
        }
    }
}
