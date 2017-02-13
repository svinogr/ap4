package ap.controller;

import ap.dao.UserInfoDAO;
import ap.dao.WorkoutDAO;
import ap.entity.EntityForXML.UserInfoXML;
import ap.entity.User;
import ap.entity.UserInfo;
import ap.services.CreateXMLService;
import ap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CertainWorkoutController {
    @Autowired
    WorkoutDAO workoutDAO;
    @Autowired
    CreateXMLService createXMLService;
    @Autowired
    UserInfoDAO userInfoDAO;

    @Autowired
    UserServices userServices;
//удалить?
    @RequestMapping(value = "/curtainUserRequest")
    public void getPageCertainUser(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
    }

    @RequestMapping(value = "/curtainUser", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public String getPageWithWorkoutCurtainUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = userInfoDAO.getAllByParentKey(Integer.parseInt(request.getParameter("id"))).get(0);
        UserInfoXML userInfoXML = new UserInfoXML(userInfo);
        model.addAttribute("userInfoXML", userInfoXML);
        return "certainUser";
    }
    @RequestMapping(value = "/getXmlAllWorkoutsCertain", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"}, params = {"id"})
    @Transactional
    public
    @ResponseBody
    String getXML(HttpServletRequest request, HttpServletResponse response) {

        User user = userServices.getById(Integer.parseInt(request.getParameter("id")));
        return createXMLService.getUserXML(user).toString();
    }
}
