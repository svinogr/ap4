package ap.services.servicesimpl;

import ap.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    Environment environment;

    @Override
    public void sendSMTPforRegistration(String to, String body) {

        MimeMessage simpleMailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(simpleMailMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            String message="Здравствуйте. Для завершения регистрации проидите по ссылке ";
            mimeMessageHelper.setText(message+" "+body);
            mimeMessageHelper.setSubject("Регистрация на сервисе U-Pump");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.err.println(simpleMailMessage);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendForgetPass(String to, String body) {
        //TODO обьеденить с регитсрашн
        MimeMessage simpleMailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(simpleMailMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            String message="Здравствуйте. Для смены пароля пройдите по ссылке ";
            mimeMessageHelper.setText(message+environment.getRequiredProperty("mail.linkrememberpass")+body);
            mimeMessageHelper.setSubject("Востановление пароля U-Pump");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.err.println(simpleMailMessage);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendMailToDeveloper(String from, String body) {
        //TODO обьеденить с регитсрашн
        MimeMessage simpleMailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(simpleMailMessage, true, "UTF-8");
            mimeMessageHelper.setText(body+". С Уважением, "+from);
            mimeMessageHelper.setTo(environment.getRequiredProperty("mail.to"));
            mimeMessageHelper.setSubject("Обратная связь U-Pump");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.err.println(simpleMailMessage);
        javaMailSender.send(simpleMailMessage);
    }


}
