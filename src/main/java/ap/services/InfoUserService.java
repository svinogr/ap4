package ap.services;

import ap.entity.EntityForXML.UserInfoXML;
import ap.entity.UserInfo;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface InfoUserService {
    String upload(MultipartFile file) throws IOException;
    UserInfo getUserInfoByParentId(int parentID);
    UserInfo getUserInfoByUserInfoId(int userInfoId);
    void updateUserInfo(UserInfoXML userInfoXML);
    UserInfoXML validUserInfoXML(UserInfoXML userInfoXML, BindingResult bindingResult);
}
