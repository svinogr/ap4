package ap.controller;

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

    @RequestMapping(value ="/confidential/addNewTry" , method = RequestMethod.GET, params = {"id","weight","repeat","tries"})
    @Transactional
    public void addNewTry(HttpServletRequest request, HttpServletResponse response){
        int idExercise = Integer.parseInt(request.getParameter("id"));
        int weight =Integer.parseInt(request.getParameter("weight"));
        int repeat =Integer.parseInt(request.getParameter("repeat"));
        int quantityTries=Integer.parseInt(request.getParameter("tries"));
        Exercise exercise = new Exercise();
        exercise.setId(idExercise);
        for (int i=0; i<quantityTries; i++) {
            Try tries = new Try();
            tries.setWeight(weight);
            tries.setRepeat(repeat);
            tries.setParentid(exercise);
            try {
                tryDAO.add(tries);
                response.setStatus(200);
            } catch (HibernateException e) {
                response.setStatus(400);
            }
        }
    }


    @RequestMapping(value = "/confidential/deleteTry", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public void deleteTry(HttpServletRequest request, HttpServletResponse response) {
        int idTry = Integer.parseInt(request.getParameter("id"));
        System.out.println("номер подхода для удаления"+idTry);
        try {
            Try tries =tryDAO.getById(idTry);
            if(userServices.allow(tries.getParentid().getParentid().getParentid().getId())) {
                tryDAO.delete(tries);
            }
        }catch ( HibernateException e){
            response.setStatus(400);
        }
        response.setStatus(200);
    }
    @RequestMapping(value = "/confidential/updateTry", method = RequestMethod.GET, params = {"id", "name", "updateVar"})
    @Transactional
    public void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("update try");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int updateVar = Integer.parseInt(request.getParameter("updateVar"));
        try {
            Try tries = tryDAO.getById(id);
            if(userServices.allow(tries.getParentid().getParentid().getParentid().getId())) {
                switch (name) {
                    case "weight":
                        tries.setWeight(updateVar);
                        break;
                    case "repeat":
                        tries.setRepeat(updateVar);
                }
            }

        }catch (HibernateException e){
            response.setStatus(400);
        }

        response.setStatus(200);

    }

}
