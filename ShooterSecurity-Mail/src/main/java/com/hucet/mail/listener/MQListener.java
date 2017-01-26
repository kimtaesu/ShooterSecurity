package com.hucet.mail.listener;

import com.hucet.dto.mq.MailUserInfoDto;
import com.hucet.mail.service.MailSendService;
import com.hucet.mail.type.EmailType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;


public interface MQListener {
    void onReceiveredMailForCert(MailUserInfoDto mailUserInfoDto);

    @Slf4j
    class MQListenerImpl implements MQListener {

        @Autowired
        MailSendService mailSendService;

        @Override
        public void onReceiveredMailForCert(@Valid MailUserInfoDto mailUserInfoDto) {
            log.info(mailUserInfoDto.toString());
            mailSendService.mailSend(EmailType.EMAIL_CERT, mailUserInfoDto);
        }
    }
}
