package ap.controller;

import ap.dao.WorkoutDAO;
import ap.dao.daoimpl.WorkoutDAOImpl;
import ap.entity.User;
import ap.entity.Workout;
import ap.services.CreateXMLService;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CreateXMLService createXMLService;

    @Autowired
    UserServices userServices;

    @Autowired
    WorkoutDAO workoutDAO;

    @RequestMapping("/test")
    public ModelAndView getTestPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        System.err.println("вызов тест контроллер");
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(user.getId());

        } catch (ClassCastException e) {
        }

        return modelAndView;
    }

    @RequestMapping(value = "XML")
    public ModelAndView getXMLPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("xmltest");
        return modelAndView;
    }

    @RequestMapping(value = "getxmlUser", method = RequestMethod.GET, produces = "application/xml")
    @Transactional
    public
    @ResponseBody
    String getXML() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user = userServices.getById(user.getId());

        return createXMLService.getXML(user).toString();
    }

    @RequestMapping(value = "getXmlWorkout", method = RequestMethod.GET, produces = "application/xml", params = {"id"})

    public
    @ResponseBody
    @Transactional
    String getXMLWorkout(HttpServletRequest request, HttpServletResponse response) {
        Workout workout=null;
        try {
            workout = workoutDAO.getById(Integer.parseInt(request.getParameter("id")));
        } catch (HibernateException e) {response.setStatus(400);}
            System.out.println("номер тренровки " + Integer.parseInt(request.getParameter("id")));
            return createXMLService.getXML(workout).toString();
    }

    @RequestMapping(value = "/addNewWorkout", method = RequestMethod.GET, params = {"name"})
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

    @RequestMapping(value = "/deleteWorkout", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public void deleteWorkout(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Workout workout = workoutDAO.getById(id);
        workoutDAO.delete(workout);

        System.out.println(id);
        response.setStatus(200);

    }


    @RequestMapping(value = "/aut")
    public void aut() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getId());
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
