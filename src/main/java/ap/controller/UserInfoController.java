package ap.controller;

import ap.dao.UserInfoDAO;
import ap.entity.UploadImageException;
import ap.entity.User;
import ap.entity.UserInfo;
import ap.entity.Workout;
import ap.services.InfoUserService;
import ap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserInfoController {

    @Autowired
    InfoUserService infoUserService;
    @Autowired
    UserServices userServices;
    @Autowired
    UserInfoDAO userInfoDAO;


    @RequestMapping(value = "/infoUser", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public String getInfoUser(Model model, HttpServletRequest request, HttpServletResponse response) {

        UserInfo userInfo = userInfoDAO.getById(Integer.parseInt(request.getParameter("id")));
        System.err.println(userInfo);
        if (userInfo != null) {
            model.addAttribute("userInfo", userInfo);
        }
        return "infoStats";
    }


    @RequestMapping(value = "confidential/myInfo", method = RequestMethod.GET)
    @Transactional
    public String getInfoLoginUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        int idLoggedUser = userServices.getLoggedUser().getId();
        UserInfo userInfo = userInfoDAO.getAllByParentKey(idLoggedUser).get(0);
        if (userInfo != null) {
            model.addAttribute("userInfo", userInfo);
        }
        return "myInfoStats";
    }

    @RequestMapping(value = "confidential/myInfoForChange", method = RequestMethod.GET )
    @Transactional
    public String changeLoginUserStats(Model model, HttpServletRequest request, HttpServletResponse response) {
        int idLoggedUser = userServices.getLoggedUser().getId();
        UserInfo userInfo = userInfoDAO.getAllByParentKey(idLoggedUser).get(0);
        if (userInfo != null) {
            model.addAttribute("userInfo", userInfo);
        }
        return "myInfoForChange";
    }

    @RequestMapping(value = "confidential/myInfoForChange", method = RequestMethod.POST )
    @Transactional
    public String changeLoginUserStats(@Valid @ModelAttribute("userInfo") UserInfo userInfo, BindingResult bindingResult, @RequestParam(value = "file", required = false) MultipartFile image, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "error");
            model.addAttribute("result", "Форма заполнена с ошибками");
            UserInfo updateUserInfo = userInfoDAO.getById(userInfo.getId());
            model.addAttribute("userInfo", updateUserInfo);
            return "myInfoForChange";
        }
        if (userInfo != null) {
            System.err.println(userInfo);
            UserInfo updateUserInfo = userInfoDAO.getById(userInfo.getId());
            updateUserInfo.setName(userInfo.getName());
            updateUserInfo.setAge(userInfo.getAge());
            updateUserInfo.setWeight(userInfo.getWeight());
            updateUserInfo.setHeight(userInfo.getHeight());
            updateUserInfo.setExperience(userInfo.getExperience());
            updateUserInfo.setDescription(userInfo.getDescription());
            try {
                if (!image.isEmpty()) {
                    String imageForBD= infoUserService.upload(image);
                    updateUserInfo.setImage(imageForBD);
                }
            } catch (UploadImageException e) {
                bindingResult.reject(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            userInfoDAO.update(updateUserInfo);
            User user  = userServices.getById(updateUserInfo.getParentid().getId());
            for(Workout workout:user.getWorkoutList()){
                workout.setAuthor(userInfo.getName());
            }
            model.addAttribute("userInfo", updateUserInfo);
            model.addAttribute("result", "Изменение внесены");
        }
        return "myInfoForChange";
    }
}
