package ap.controller;

import ap.dao.WorkoutDAO;
import ap.entity.Exercise;
import ap.entity.User;
import ap.entity.Workout;
import ap.services.CreateWorkoutXMLService;
import ap.services.CreateXMLService;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class MyWorkoutController {

    @Autowired
    CreateXMLService createXMLService;

    @Autowired
    UserServices userServices;

    @Autowired
    WorkoutDAO workoutDAO;


    @Autowired
    CreateWorkoutXMLService createWorkoutXMLService;

    @RequestMapping(value = "/confidential/myworkout")
    public String getMyWorkoutPage() {
        return "myworkout";
    }

    @RequestMapping(value = "/confidential/altworkout")
    public String altworkout() {
        return "altworkout";
    }

    @RequestMapping(value = "/confidential/getXmlAllWorkouts", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"})
    @Transactional
    public
    @ResponseBody
    String getXML() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userServices.getById(user.getId());
        return createXMLService.getXML(user).toString();
    }

    @RequestMapping(value = "/confidential/getXmlWorkout", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"}, params = {"id"})
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

        return createWorkoutXMLService.getXML(workout).toString();

    }

    @RequestMapping(value = "/confidential/addNewWorkout", method = RequestMethod.GET, params = {"name"})
    @Transactional
    public void addNewWorkout(HttpServletRequest request, HttpServletResponse response) {
        String nameOfNewWorkout = request.getParameter("name");
        System.out.println(nameOfNewWorkout);
        if (nameOfNewWorkout != null) {
            int idLoginUser = getLoginUserId();
            User user = userServices.getById(idLoginUser);
            int positionNewWorkout = getPosition(user.getWorkoutList());
            try {
                workoutDAO.createNewWorkout(nameOfNewWorkout, positionNewWorkout, user);
                response.setStatus(200);
            } catch (HibernateException e) {
                response.setStatus(400);
            }

        }
    }

    @RequestMapping(value = "/confidential/deleteWorkout", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public void deleteWorkout(HttpServletRequest request, HttpServletResponse response) {

        int idWorkout = Integer.parseInt(request.getParameter("id"));
        System.out.println("номер тренировки на удаление" + idWorkout);
        Workout workout = workoutDAO.getById(idWorkout);
        try {
            if(userServices.allow(workout.getParentid().getId())) {
                workoutDAO.delete(workout);
            }
        } catch (HibernateException e) {
            response.setStatus(400);
        }
        response.setStatus(200);
    }

    @RequestMapping(value = "/confidential/updateWorkout", method = RequestMethod.GET, params = {"id", "name", "updateVar"})
    @Transactional
    public void update(HttpServletRequest request, HttpServletResponse response) {
        System.err.println("update workout");
        int id = Integer.parseInt(request.getParameter("id"));
        String updateVar = request.getParameter("updateVar");
        try {
            Workout workout = workoutDAO.getById(id);
            if(userServices.allow(workout.getParentid().getId())) {
                workout.setName(updateVar);
            }
        }catch (HibernateException e){
            System.err.println("ошибка");
            response.setStatus(400);
        }
        response.setStatus(200);
    }

    

    private int getLoginUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int idLoginUser = user.getId();
        return idLoginUser;
    }

    private int getPosition(List list) {
        return list.size();
    }
}
