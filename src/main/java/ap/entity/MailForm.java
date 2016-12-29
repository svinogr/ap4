package ap.entity;

public class MailForm {
    String from;
    String to;
    String body;

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
