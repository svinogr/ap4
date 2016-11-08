package ap.controller;

import ap.dao.WorkoutDAO;
import ap.entity.User;
import ap.entity.Workout;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


@Controller
public class UserController {

    @Autowired
    UserServices userServices;
    @Autowired
    WorkoutDAO workoutDAO;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView Userregistration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registrationForme");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationResponse(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "error");
            model.addAttribute("result", "форма заполнена с ошибками");
            return "registrationForme";
        }
        System.out.println(user.getName());
        System.out.println(user.getLogin());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
       try {
           userServices.registrationUser(user);
       }catch (Exception e){
           System.out.println("такой Login или Email уже зарегистрирован");
           model.addAttribute("result","такой Login или Email уже зарегистрирован");
           return "registrationForme";
       }
        model.addAttribute("result", "Пользователь " +user.getName()+" добавлен");
        return "registrationForme";
    }


}
