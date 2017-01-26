package com.hucet.mail;

import com.hucet.dto.mq.MailUserInfoDto;
import com.hucet.mail.config.MailCProperties;
import com.hucet.mail.type.EmailType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;

/**
 * Created by taesu on 2017-01-26.
 */
@Component
public class MailContentConstructor {

    @Autowired
    MailCProperties mailCProperties;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    public MimeMessage createMimeMessageForEmailCertification(MailUserInfoDto dto) throws MessagingException {
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        final MimeMessageHelper message; // true = multipart

        message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setFrom(mailCProperties.getEmail());
        message.setSubject("test");
        message.setTo(dto.getUserEmail());
        final String htmlContent = createHtmlContentForEmailCertification(EmailType.EMAIL_CERT, dto);

        message.setText(htmlContent, true); // true = isHtml

        return mimeMessage;
    }

    private String createHtmlContentForEmailCertification(EmailType emailCert, MailUserInfoDto dto) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        // Create the HTML body using Thymeleaf
        final Context ctx = new Context();
        ctx.setVariable("userName", dto.getUserName());
//        ctx.setVariable("link", dto.getLink());
//        ctx.setVariable("expiredDate", format.format(dto.getExpiredDate()));
        return this.templateEngine.process(emailCert.getTemplateName(), ctx);
    }
}
