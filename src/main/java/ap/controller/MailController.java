package ap.controller;

import ap.entity.User;
import ap.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Controller
public class MailController {

    @Autowired
    MailService mailService;

  /* *//* // для токнов
    @Autowired
    PersistentTokenRepository persistentTokenRepository;*//*

    @Autowired
    UserDetailsService userDetailsService;

    @RequestMapping(value = "/mail")
    public void sendMailTest(){
        Date date = new Date();
       persistentTokenRepository.createNewToken(new PersistentRememberMeToken("dsd","1","dwd",date));
        mailService.sendSMTPforRegistration("3294747@mail.ru",persistentTokenRepository.getTokenForSeries("1").getTokenValue());
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("это токен"+persistentTokenRepository.getTokenForSeries("1").toString());

    }*/
}
