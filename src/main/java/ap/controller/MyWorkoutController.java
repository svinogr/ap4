package ap.controller;

import ap.dao.WorkoutDAO;
import ap.entity.User;
import ap.entity.Workout;
import ap.services.*;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyWorkoutController {

    @Autowired
    Environment environment;

    @Autowired
    CreateXMLService createXMLService;

    @Autowired
    UserServices userServices;

    @Autowired
    WorkoutDAO workoutDAO;


    @Autowired
    RateServices rateServices;

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
    @ResponseBody
    public String getXML() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userServices.getById(user.getId());
        return createXMLService.getUserXML(user).toString();
    }

    @RequestMapping(value = "/confidential/getXmlWorkout", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"}, params = {"id"})
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
        return createXMLService.getWorkoutXML(workout).toString();
    }

    @RequestMapping(value = "/confidential/addNewWorkout", method = RequestMethod.GET, params = {"name"})
    @Transactional
    public void addNewWorkout(HttpServletRequest request, HttpServletResponse response) {
        String nameOfNewWorkout = request.getParameter("name");
        if (nameOfNewWorkout != null) {
            int idLoginUser = userServices.getLoggedUser().getId();
            User user = userServices.getById(idLoginUser);
            if (user.getWorkoutList().size() < 10) {
                int positionNewWorkout = user.getWorkoutList().size();
                try {
                    workoutDAO.createNewWorkout(nameOfNewWorkout, positionNewWorkout, user);
                    response.setStatus(200);
                } catch (HibernateException e) {
                    response.setStatus(400);
                }
            } else response.setStatus(400);
        }
    }

    @RequestMapping(value = "/confidential/deleteWorkout", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public void deleteWorkout(HttpServletRequest request, HttpServletResponse response) {
        int idWorkout = Integer.parseInt(request.getParameter("id"));
        System.out.println("номер тренировки на удаление" + idWorkout);
        Workout workout = workoutDAO.getById(idWorkout);
        try {
            if (userServices.allow(workout.getParentid().getId())) {
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
            if (userServices.allow(workout.getParentid().getId())) {
                workout.setName(updateVar.toLowerCase());
            }
        } catch (HibernateException e) {
            System.err.println("ошибка");
            response.setStatus(400);
        }
        response.setStatus(200);
    }

    @RequestMapping(value = "/copyWorkout", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"}, params = {"id"})
    public
    @ResponseBody
    @Transactional
    void copyWorkout(HttpServletRequest request, HttpServletResponse response) {
        if (request.getUserPrincipal() == null) {
            response.setStatus(401);
        } else {
            int idWorkout = Integer.parseInt(request.getParameter("id"));
            User user = getLoginUser();
            try {
                workoutDAO.copyWorkout(idWorkout, user);
            } catch (HibernateException e) {
                response.setStatus(400);
            }
            response.setStatus(200);
            System.out.println("тренировка скопирована");
        }
    }

    @RequestMapping(value = "/rate", method = RequestMethod.GET, params = {"id", "rate"})
    @Transactional
    public void rate(HttpServletRequest request, HttpServletResponse response) {
        if (request.getUserPrincipal() == null) {
            response.setStatus(401);
        } else {
            int idWorkout = Integer.parseInt(request.getParameter("id"));
            int rate = Integer.parseInt(request.getParameter("rate"));
            int userId = getLoginUserId();
            try {
                rateServices.rate(idWorkout, rate, userId);
            } catch (HibernateException e) {
                response.setStatus(400);
            }
            response.setStatus(200);
        }
    }

    private int getLoginUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int idLoginUser = user.getId();
        return idLoginUser;
    }

    private User getLoginUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }


}
