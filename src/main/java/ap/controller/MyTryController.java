package ap.controller;

import ap.dao.ExersiceDAO;
import ap.dao.TryDAO;
import ap.entity.Exercise;
import ap.entity.Try;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MyTryController {
    @Autowired
    TryDAO tryDAO;
    @Autowired
    UserServices userServices;
    @Autowired
    ExersiceDAO exersiceDAO;

    @RequestMapping(value = "/confidential/addNewTry", method = RequestMethod.GET, params = {"id", "weight", "repeat", "tries"})
    @Transactional
    public void addNewTry(HttpServletRequest request, HttpServletResponse response) {
        int idExercise = Integer.parseInt(request.getParameter("id"));
        double weight = Double.parseDouble(request.getParameter("weight"));
        int repeat = Integer.parseInt(request.getParameter("repeat"));
        int quantityTries = Integer.parseInt(request.getParameter("tries"));
        Exercise exercise = exersiceDAO.getById(idExercise);
        for (int i = 0; i < quantityTries; i++) {
            Try tries = new Try();
            tries.setWeight(weight);
            tries.setRepeat(repeat);
            tries.setParentid(exercise);
            try {
                System.err.println(tries.getParentid().getId());
                System.err.println(tries.getParentid().getParentid().getWorkoutId());
                if (userServices.allow(tries.getParentid().getParentid().getParentid().getId())) {
                    tryDAO.add(tries);
                    response.setStatus(200);
                }
            } catch (HibernateException e) {
                response.setStatus(400);
            }
        }
    }


    @RequestMapping(value = "/confidential/deleteTry", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public void deleteTry(HttpServletRequest request, HttpServletResponse response) {
        int idTry = Integer.parseInt(request.getParameter("id"));
        try {
            Try tries = tryDAO.getById(idTry);
            if (userServices.allow(tries.getParentid().getParentid().getParentid().getId())) {
                tryDAO.delete(tries);
            }
        } catch (HibernateException e) {
            response.setStatus(400);
        }
        response.setStatus(200);
    }

    @RequestMapping(value = "/confidential/updateTry", method = RequestMethod.GET, params = {"id", "name", "updateVar"})
    @Transactional
    public void update(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int updateVar = Integer.parseInt(request.getParameter("updateVar"));
        try {
            Try tries = tryDAO.getById(id);
            if (userServices.allow(tries.getParentid().getParentid().getParentid().getId())) {
                switch (name) {
                    case "weight":
                        tries.setWeight(updateVar);
                        break;
                    case "repeat":
                        tries.setRepeat(updateVar);
                }
            }
        } catch (HibernateException e) {
            response.setStatus(400);
        }
        response.setStatus(200);
    }

    @RequestMapping(value = "/confidential/updateColorTry", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public void updateColorTry(HttpServletRequest request, HttpServletResponse response) {
        System.err.println(request.getParameter("id"));
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Try tries = tryDAO.getById(id);
            if (tries.isDone()) {
                tries.setDone(false);
            } else tries.setDone(true);
        } catch (HibernateException e) {
            response.setStatus(400);
        }
    }
}
