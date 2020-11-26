package project.service.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailProperties {

    @Value("${mail.defaultMessage}")
    private String defaultMessage;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Value("${mail.debug}")
    private String debug;

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(this.host);
        mailSender.setPort(this.port);
        mailSender.setUsername(this.username);
        mailSender.setPassword(this.password);

        Properties properties = mailSender.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", this.protocol);
        properties.setProperty("mail.debug", this.debug);

        return mailSender;
    }
}
