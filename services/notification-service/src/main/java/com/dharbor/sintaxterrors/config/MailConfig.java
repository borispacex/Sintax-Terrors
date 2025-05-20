package com.dharbor.sintaxterrors.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    @Qualifier("gmailSender")
    public JavaMailSender gmailSender(
            @Value("${mail.gmail.host}") String host,
            @Value("${mail.gmail.port}") int port,
            @Value("${mail.gmail.username}") String username,
            @Value("${mail.gmail.password}") String password
    ) {
        return buildSender(host, port, username, password, true, true);
    }

    @Bean
    @Qualifier("outlookSender")
    public JavaMailSender outlookSender(
            @Value("${mail.outlook.host}") String host,
            @Value("${mail.outlook.port}") int port,
            @Value("${mail.outlook.username}") String username,
            @Value("${mail.outlook.password}") String password
    ) {
        return buildSender(host, port, username, password, true, true);
    }

    private JavaMailSender buildSender(String host, int port, String username, String password,
                                       boolean auth, boolean starttls) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        return mailSender;
    }
}


