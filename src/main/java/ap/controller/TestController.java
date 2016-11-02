package ap.controller;

import ap.dao.ExersiceDAO;
import ap.dao.TryDAO;
import ap.dao.WorkoutDAO;
import ap.dao.daoimpl.WorkoutDAOImpl;
import ap.entity.Exercise;
import ap.entity.Try;
import ap.entity.User;
import ap.entity.Workout;
import ap.services.CreateExerciseXMLService;
import ap.services.CreateWorkoutXMLService;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @Autowired
    ExersiceDAO exersiceDAO;

    @Autowired
    TryDAO tryDAO;

    @Autowired
    CreateWorkoutXMLService createWorkoutXMLService;

    @Autowired
    CreateExerciseXMLService createExerciseXMLService;

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

    @RequestMapping(value = "/confidential/modal")
    public String modal() {
        return "modal";
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
