package com.hucet.mail.listener;

import com.hucet.dto.mq.MailSendDto;
import lombok.extern.slf4j.Slf4j;


public interface MQListener {
    void onReceiveredMailSend(MailSendDto mailSendDto);

    @Slf4j
    class MQListenerImpl implements MQListener {

        @Override
        public void onReceiveredMailSend(MailSendDto mailSendDto) {
            log.info(mailSendDto.toString());
        }
    }
}
