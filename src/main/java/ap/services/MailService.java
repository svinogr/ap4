package ap.services;

import javax.mail.MessagingException;

public interface MailService {
    void sendSMTPforRegistration(String to, String body);
    void sendForgetPass(String to, String body);
}
