package com.hucet.mail.config;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Data
@Configuration
@ToString
public class MailConfig {
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String MAIL_DEBUG = "mail.debug";
    private static final String MAIL_SMTP_SSL_SOCKET_FACTORY = "mail.smtp.ssl.socketFactory";

    @Autowired
    MailCProperties mailCProperties;

    @Bean
    public JavaMailSender mailSender() throws GeneralSecurityException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailCProperties.getHost());
        mailSender.setProtocol(mailCProperties.getProtocol());
        mailSender.setPort(mailCProperties.getPort());
        mailSender.setUsername(mailCProperties.getEmail());
        mailSender.setPassword(mailCProperties.getPassword());
        mailSender.setDefaultEncoding(mailCProperties.getDefaultEncoding());
        mailSender.setJavaMailProperties(getMailProperties());
        return mailSender;
    }

    @Bean
    Properties getMailProperties() throws GeneralSecurityException {
        final Properties properties = new Properties();
        properties.put(MAIL_SMTP_AUTH, mailCProperties.getSmtp().getAuth());
        properties.put(MAIL_SMTP_STARTTLS, mailCProperties.getSmtp().getStartTlsEnable());
        properties.put(MAIL_DEBUG, mailCProperties.getDebug());

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put(MAIL_SMTP_SSL_SOCKET_FACTORY, sf);
        return properties;
    }

    @Bean
    public AsyncTaskExecutor emailExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(15);
        executor.setRejectedExecutionHandler(abortPolicy());
        return executor;
    }

    @Bean
    public RejectedExecutionHandler abortPolicy() {
        return new ThreadPoolExecutor.AbortPolicy();
    }
}