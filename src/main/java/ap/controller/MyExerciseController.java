package ap.controller;

import ap.dao.ExersiceDAO;
import ap.dao.WorkoutDAO;
import ap.entity.Exercise;
import ap.entity.Workout;
import ap.services.CreateExerciseXMLService;
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
public class MyExerciseController {
    @Autowired
    WorkoutDAO workoutDAO;

    @Autowired
    ExersiceDAO exersiceDAO;

    @Autowired
    UserServices userServices;

    @Autowired
    CreateExerciseXMLService createExerciseXMLService;

    @RequestMapping(value = "/confidential/getXmlExercise", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"}, params = {"id"})
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
    @RequestMapping(value = "/confidential/addNewExercise", method = RequestMethod.GET, params = {"id", "name"})
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
    @RequestMapping(value = "/confidential/deleteExercise", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public void deleteExercise(HttpServletRequest request, HttpServletResponse response) {
        int idExercise = Integer.parseInt(request.getParameter("id"));
        System.out.println("номер упражнения для удаления"+idExercise);
        try {
            Exercise exercise =exersiceDAO.getById(idExercise);
            if(userServices.allow(exercise.getParentid().getParentid().getId())) {
                exersiceDAO.delete(exercise);
            }
        }catch ( HibernateException e){
            response.setStatus(400);
        }
        response.setStatus(200);
    }

    @RequestMapping(value = "/confidential/updateExercise", method = RequestMethod.GET, params = {"id", "name", "updateVar"})
    @Transactional
    public void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("update exercise");
        int id = Integer.parseInt(request.getParameter("id"));
        String updateVar = request.getParameter("updateVar");
        try {
            Exercise exercise = exersiceDAO.getById(id);
            if(userServices.allow(exercise.getParentid().getParentid().getId())) {
                exercise.setName(updateVar);
            }
        }catch (HibernateException e){
            response.setStatus(400);
        }
        response.setStatus(200);
    }
}
