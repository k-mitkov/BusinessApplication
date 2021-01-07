package javaproject.BusinessApplication.config;

import javaproject.BusinessApplication.util.EmailSender;
import javaproject.BusinessApplication.util.EmailSenderImpl;
import javaproject.BusinessApplication.util.TweetSender;
import javaproject.BusinessApplication.util.TweetSenderImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public EmailSender emailSender() {
        return new EmailSenderImpl();
    }

    @Bean
    public TweetSender tweetSender() {
        return new TweetSenderImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}