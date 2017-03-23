package ap.entity.EntityForXML;

import ap.entity.Post;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@XmlRootElement(name = "postXML")
public class PostXML {

    private int id;

    private int userId;

    private Date date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @XmlElement(name = "title")
    private String title;

    @NotEmpty
    @Size(max = 1000, message = "текст не должен отсутсвовать")
    private String description;

    private String link;


    public PostXML() {
    }

    public PostXML(Post post) {
        this.title=post.getTitle();
        this.id = post.getId();
        this.userId = post.getParentid().getId();
        this.date = post.getDate();
        this.description = post.getDescription();
        this.link = post.getLink();
    }

    public int getId() {
        return id;
    }

    @XmlElement(name = "postId")
    public void setId(int id) {
        this.id = id;
    }

    public int getParentid() {
        return userId;
    }

    @XmlElement(name = "userId")
    public void setParentid(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    @XmlElement(name = "date")
    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    @XmlElement(name = "link")
    public void setLink(String link) {
        this.link = link;
    }
}
