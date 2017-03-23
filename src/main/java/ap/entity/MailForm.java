package ap.entity;

import org.hibernate.validator.constraints.NotEmpty;

public class MailForm {
    @NotEmpty (message = "пожалуйста, введите правильный email")
    private String from;
    private String to;
    @NotEmpty(message = "пожалуйста, введите текст сообщения ")
    private String body;

    public MailForm(String from, String to, String body) {
        this.from = from;
        this.to = to;
        this.body = body;
    }

    public MailForm() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
