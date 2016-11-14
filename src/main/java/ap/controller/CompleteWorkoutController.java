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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CompleteWorkoutController {
    @Autowired
    UserServices userServices;
    @Autowired
    CreateXMLService createXMLService;
    @Autowired
    WorkoutDAO workoutDAO;
    @Autowired
    CreateWorkoutXMLService createWorkoutXMLService;

    @RequestMapping(value = "complete")
    public String getMyWorkoutPage() {
        return "complete";
    }

    @RequestMapping(value = "/getXmlAllWorkoutsComplete", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"})
    @Transactional
    public
    @ResponseBody
    String getXML() {
        User user = userServices.getUser("complete");
        return createXMLService.getXML(user).toString();
    }

    @RequestMapping(value = "/getXmlWorkoutComplete", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"}, params = {"id"})
    public
    @ResponseBody
    @Transactional
    String getXMLWorkout(HttpServletRequest request, HttpServletResponse response) {
        Workout workout = null;
        System.out.println("номер тренровки " + Integer.parseInt(request.getParameter("id")));
        try {
            workout = workoutDAO.getById(Integer.parseInt(request.getParameter("id")));
        } catch (HibernateException e) {
            response.setStatus(400);
        }
        response.setStatus(200);
        return createWorkoutXMLService.getXML(workout).toString();
    }
}
