package ap.services.servicesimpl;

import ap.dao.UserInfoDAO;
import ap.entity.EntityForXML.UserInfoXML;
import ap.entity.UploadImageException;
import ap.entity.UserInfo;
import ap.services.InfoUserService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class InfoUserServiceImpl implements InfoUserService {

    @Autowired
    UserInfoDAO userInfoDAO;

    @Override
    public String upload(MultipartFile file) throws UploadImageException, IOException {
        validateImage(file);
        byte[] base = Base64.encodeBase64(file.getBytes());
        String name = new String(base, "UTF-8");
        return name;
    }

    @Override
    @Transactional
    public UserInfo getUserInfoByParentId(int parentID) {
        UserInfo userInfo = null;
        try {
            userInfo = userInfoDAO.getAllByParentKey(parentID).get(0);
        } catch (IndexOutOfBoundsException e) {
            //NOP
        }
        return userInfo;
    }

    @Override
    public UserInfo getUserInfoByUserInfoId(int userInfoId) {
        UserInfo userInfo = null;
        try {
            userInfo = userInfoDAO.getById(userInfoId);
            return userInfo;

        }catch (NullPointerException e){
            //NOP
        }
        return userInfo;
    }

    @Override
    @Transactional
    public void updateUserInfo(UserInfoXML userInfoXML) {
        UserInfo userInfo = userInfoDAO.getById(userInfoXML.getUserInfoId());
        userInfo.setName(userInfoXML.getName());
        userInfo.setAge(userInfoXML.getAge());
        userInfo.setWeight(userInfoXML.getWeight());
        userInfo.setHeight(userInfoXML.getHeight());
        userInfo.setDescription(userInfoXML.getDescription());
        //TODO and link

        userInfoDAO.update(userInfo);

    }

    @Override
    public UserInfoXML validUserInfoXML(UserInfoXML userInfoXML, BindingResult bindingResult) {
        Map<String, String> mapErrors = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            mapErrors.put(error.getField(), error.getDefaultMessage());
        }
        if (mapErrors.containsKey("name")) {
            userInfoXML.setName(mapErrors.get("name"));
        }
        if (mapErrors.containsKey("age")) {
            userInfoXML.setAge(Integer.parseInt(mapErrors.get("age")));
        }
        if (mapErrors.containsKey("weight")) {
            userInfoXML.setWeight(Integer.parseInt(mapErrors.get("weight")));
        }
        if (mapErrors.containsKey("height")) {
            userInfoXML.setHeight(Integer.parseInt(mapErrors.get("height")));
        }
        if (mapErrors.containsKey("description")) {
            userInfoXML.setDescription(mapErrors.get("description"));
        }
        return userInfoXML;

    }

    private void validateImage(MultipartFile file) {
        if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/jpg") && !file.getContentType().equals("image/gif") && !file.getContentType().equals("image/png")) {
            throw new UploadImageException("not supported MyFormat fille");
        }
    }

}
