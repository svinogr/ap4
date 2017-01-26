package ap.entity.EntityForXML;

import ap.entity.UserInfo;

import javax.xml.bind.annotation.XmlElement;

public class UserInfoXML extends UserInfo {
    private int userId;

    public UserInfoXML(UserInfo userInfo) {
        super(userInfo);
        this.userId = userInfo.getParentid().getId();
    }

    public int getUserId() {
        return userId;
    }

    @XmlElement(name = "userId")
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
