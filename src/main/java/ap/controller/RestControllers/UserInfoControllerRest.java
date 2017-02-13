package ap.controller.RestControllers;

import ap.entity.EntityForXML.UserInfoXML;
import ap.entity.User;
import ap.entity.UserInfo;
import ap.services.InfoUserService;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v.1/user/")
public class UserInfoControllerRest {
    @Autowired
    UserServices userServices;
    @Autowired
    InfoUserService infoUserService;

    /**
     * @param response 401 if user is not authenticated
     * @return xml UserInfoXML of authenticated user
     * */
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"})
    @Transactional
    public
    @ResponseBody
    UserInfoXML getUserInfo(HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        UserInfo userInfo;
        if (user != null) {
            userInfo = infoUserService.getUserInfoByParentId(user.getId());
            UserInfoXML userInfoXML = new UserInfoXML(userInfo);
            response.setStatus(200);
            return userInfoXML;
        } else {
            response.setStatus(401);
            return null;
        }
    }

    /**
     * @param id id of user in DB
     * @param response 404 if id is not found
     * @return UserInfoXML
     */
    @RequestMapping(value = "/{id}/userInfo", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"})
    @Transactional
    public
    @ResponseBody
    UserInfoXML getUserInfoByID(@PathVariable int id, HttpServletResponse response) {
        try {
            UserInfo userInfo = infoUserService.getUserInfoByParentId(id);
            UserInfoXML userInfoXML = new UserInfoXML(userInfo);
            return userInfoXML;
        } catch (HibernateException | NullPointerException e) {
            response.setStatus(404);
            return null;
        }
    }

    /**
     * @param userInfoXML userInfoXML
     * @param bindingResult
     * @param response 401 if user is not authenticated
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.PUT, headers = "Content-Type=application/xml",
            produces = {"application/xml; charset=UTF-8"})
    @Transactional
    public
    @ResponseBody
    UserInfoXML changeUserInfo(@RequestBody @Valid UserInfoXML userInfoXML, BindingResult bindingResult, HttpServletResponse response)  {
       try {
           int userIdForChangeInfo = userInfoXML.getUserId();

           if (userServices.allow(userIdForChangeInfo)) {

               if (bindingResult.hasErrors()) {
                   response.setStatus(400);
                   return  infoUserService.validUserInfoXML(userInfoXML, bindingResult);

               }
               infoUserService.updateUserInfo(userInfoXML);
               response.setStatus(200);
               return userInfoXML;
           }

       }catch (NullPointerException e){
           response.setStatus(401);
           return null;
       }
        return null;
    }

}
