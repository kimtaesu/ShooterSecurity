package com.hucet.mail.config;

import com.sun.istack.internal.NotNull;
import com.sun.mail.util.MailSSLSocketFactory;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Data
@Configuration
@ConfigurationProperties(prefix = "mail", locations = {"classpath:properties.yml"})
@ToString
public class MailConfig {
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String MAIL_DEBUG = "mail.debug";
    private static final String MAIL_SMTP_SSL_SOCKET_FACTORY = "mail.smtp.ssl.socketFactory";

    @Data
    public static class Smtp {
        private Boolean auth;
        private Boolean startTlsRequired;
        private Boolean startTlsEnable;
    }

    private String host;
    private String protocol;
    private int port;
    private String username;
    private String password;
    private String defaultEncoding;
    private String debug;
    @NotNull
    private Smtp smtp;

    @Bean
    public JavaMailSender mailSender() throws GeneralSecurityException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(getHost());
        mailSender.setProtocol(getProtocol());
        mailSender.setPort(getPort());
        mailSender.setUsername(getUsername());
        mailSender.setPassword(getPassword());
        mailSender.setDefaultEncoding(getDefaultEncoding());
        mailSender.setJavaMailProperties(getMailProperties());
        return mailSender;
    }

    @Bean
    Properties getMailProperties() throws GeneralSecurityException {
        final Properties properties = new Properties();
        properties.put(MAIL_SMTP_AUTH, getSmtp().getAuth());
        properties.put(MAIL_SMTP_STARTTLS, getSmtp().getStartTlsEnable());
        properties.put(MAIL_DEBUG, getDebug());

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put(MAIL_SMTP_SSL_SOCKET_FACTORY, sf);
        return properties;
    }

    @Bean
    public TemplateResolver emailTemplateResolver() {
        TemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("mails/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode("HTML5");
        emailTemplateResolver.setCharacterEncoding("UTF-8");
        emailTemplateResolver.setCacheable(true);
        return emailTemplateResolver;
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