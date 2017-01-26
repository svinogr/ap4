package ap.controller;

import ap.dao.WorkoutDAO;
import ap.entity.User;
import ap.services.CreateXMLService;
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
public class AllWorkoutsController {

    @Autowired
    WorkoutDAO workoutDAO;

    @Autowired
    CreateXMLService createXMLService;

    @RequestMapping(value = "allWorkouts")
    public String getAllWorkoutPage(Model model) {

        model.addAttribute("count", workoutDAO.getCountAllWorkout());
        return "allWorkouts";
    }
    @RequestMapping(value = "/allWorkoutsXml", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"}, params = {"page"})
    @Transactional
    @ResponseBody
    public String getAllWorkoutXml(HttpServletRequest request, HttpServletResponse response) {
        int numberPage = Integer.parseInt(request.getParameter("page"))*20;
        User user = new User();
        user. setWorkoutList(workoutDAO.getListAllWorkout(numberPage));
        return  createXMLService.getUserXML(user).toString();
    }
}
