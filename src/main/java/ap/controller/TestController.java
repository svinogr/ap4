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

    @RequestMapping(value = "getXmlAllWorkouts", method = RequestMethod.GET, produces = "application/xml")
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
        Workout workout = null;
        System.out.println("номер тренровки " + Integer.parseInt(request.getParameter("id")));
        try {
            workout = workoutDAO.getById(Integer.parseInt(request.getParameter("id")));


        } catch (HibernateException e) {
            response.setStatus(400);
        }

        return createWorkoutXMLService.getXML(workout).toString();

    }
    @RequestMapping(value = "getXmlExercise", method = RequestMethod.GET, produces = "application/xml", params = {"id"})
    public
    @ResponseBody
    @Transactional
    String getXMLExercise(HttpServletRequest request, HttpServletResponse response) {
        Exercise exercise = null;
        System.out.println("номер упражнения " + Integer.parseInt(request.getParameter("id")));
              try {
            exercise = exersiceDAO.getById(Integer.parseInt(request.getParameter("id")));


        } catch (HibernateException e) {
            response.setStatus(400);
        }

        return createExerciseXMLService.getXML(exercise).toString();

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

    @RequestMapping(value = "/addNewExercise", method = RequestMethod.GET, params = {"id", "name"})
    @Transactional
    public void addNewExercise(HttpServletRequest request, HttpServletResponse response) {
        int idWorkout = Integer.parseInt(request.getParameter("id"));
        String nameofNewExercise = request.getParameter("name");
        System.out.println("запрос на с оздание упржнение для  тренировки " + idWorkout);
        System.out.println("запрос на с оздание упржнение c названием " + nameofNewExercise);

        if (nameofNewExercise != null) {
            try {
                Workout workout = workoutDAO.getById(Integer.parseInt(request.getParameter("id")));
                exersiceDAO.createNewExercise(nameofNewExercise, workout);
                response.setStatus(200);
            } catch (HibernateException e) {
                response.setStatus(400);
            }
        }
    }


    @RequestMapping(value = "/deleteWorkout", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public void deleteWorkout(HttpServletRequest request, HttpServletResponse response) {
        int idWorkout = Integer.parseInt(request.getParameter("id"));
        System.out.println("номер тренировки на удаление" +idWorkout);
        Workout workout = workoutDAO.getById(idWorkout);
       try {
           workoutDAO.delete(workout);
       }catch (HibernateException e){
           response.setStatus(400);
       }
        response.setStatus(200);
    }




    @RequestMapping(value = "/deleteExercise", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public void deleteExercise(HttpServletRequest request, HttpServletResponse response) {
        int idExercise = Integer.parseInt(request.getParameter("id"));
        System.out.println("номер упражнения для удаления"+idExercise);
       try {
           Exercise exercise =exersiceDAO.getById(idExercise);
           exersiceDAO.delete(exercise);

       }catch ( HibernateException e){
           response.setStatus(400);
       }
       response.setStatus(200);
    }

    @RequestMapping(value = "/deleteTry", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public void deleteTry(HttpServletRequest request, HttpServletResponse response) {
        int idTry = Integer.parseInt(request.getParameter("id"));
        System.out.println("номер упражнения для удаления"+idTry);
        try {
            Try tries =tryDAO.getById(idTry);
            tryDAO.delete(tries);
        }catch ( HibernateException e){
            response.setStatus(400);
        }
        response.setStatus(200);
    }


    @RequestMapping(value ="addNewTry" , method = RequestMethod.GET, params = {"id","weight","repeat"})
    @Transactional
    public void addNewTry(HttpServletRequest request, HttpServletResponse response){
        int idExercise = Integer.parseInt(request.getParameter("id"));
        int weight =Integer.parseInt(request.getParameter("weight"));
        int repeat =Integer.parseInt(request.getParameter("repeat"));
        Exercise exercise = new Exercise();
        exercise.setId(idExercise);
        Try tries = new Try();
        tries.setWeight(weight);
        tries.setRepeat(repeat);
        tries.setParentid(exercise);
        try {
            tryDAO.add(tries);
            response.setStatus(200);
        }catch (HibernateException e){
            response.setStatus(400);
        }




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
