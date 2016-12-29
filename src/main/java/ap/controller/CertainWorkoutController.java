package ap.controller;

import ap.dao.WorkoutDAO;
import ap.entity.User;
import ap.entity.Workout;
import ap.services.CreateWorkoutXMLService;
import ap.services.CreateXMLService;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;
import java.util.List;

@Controller
public class CertainWorkoutController {
    @Autowired
    WorkoutDAO workoutDAO;
    @Autowired
    CreateXMLService createXMLService;
    @Autowired
    CreateWorkoutXMLService createWorkoutXMLService;

    @Autowired
    UserServices userServices;

    @RequestMapping(value = "/curtainUserRequest")
    public void getPageCertainUser(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
    }

    @RequestMapping(value = "/curtainUser", method = RequestMethod.GET, params = {"id"})
    public String getPageWithWorkoutCurtainUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("author", request.getParameter("id"));
        return "certainUser";
    }

    @RequestMapping(value = "/getXmlAllWorkoutsCertain", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"}, params = {"id"})
    @Transactional
    public
    @ResponseBody
    String getXML(HttpServletRequest request, HttpServletResponse response) {
        String nameUser = request.getParameter("id");
        User user = userServices.getUserByName(nameUser);
        return createXMLService.getXML(user).toString();
    }

}
