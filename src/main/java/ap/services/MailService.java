package ap.services;

public interface MailService {
    void sendSMTPforRegistration(String to, String body);
    void sendForgetPass(String to, String body);
    void sendMailToDeveloper(String from, String body);
}
