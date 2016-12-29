package ap.controller;

import ap.dao.WorkoutDAO;
import ap.entity.User;
import ap.entity.Workout;
import ap.services.CreateWorkoutXMLService;
import ap.services.CreateXMLService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;
import java.util.List;

@Controller
public class BestWorkoutController {
    @Autowired
    WorkoutDAO workoutDAO;
    @Autowired
    CreateXMLService createXMLService;
    @Autowired
    CreateWorkoutXMLService createWorkoutXMLService;

    @RequestMapping(value = "best")
    public String getMyWorkoutPage() {
        return "best";
    }

    @RequestMapping(value = "/getXmlAllWorkoutsBest", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"})
    @Transactional
    public
    @ResponseBody
    String getXMLAll(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        int limitWorkout=50;
        List<Workout> workoutList;
        try {
            workoutList = workoutDAO.getListWorkout(limitWorkout);
            user.getWorkoutList().addAll(workoutList);
        } catch (HibernateException e) {
            response.setStatus(400);
        }
        StringWriter xml = createXMLService.getXML(user);
        System.out.println(xml.toString());
        response.setStatus(200);
        return xml.toString();
    }
    @RequestMapping(value = "/getXmlWorkoutBest", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"}, params = {"id"})
    public
    @ResponseBody
    @Transactional
    String getXMLWorkout(HttpServletRequest request, HttpServletResponse response) {
        Workout workout = null;
        try {
            workout = workoutDAO.getById(Integer.parseInt(request.getParameter("id")));
        } catch (HibernateException e) {
            response.setStatus(400);
        }
        response.setStatus(200);
        return createWorkoutXMLService.getXML(workout).toString();
    }
}
