package ap.controller;

import ap.entity.User;
import ap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    UserServices userServices;

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
            model.addAttribute("result", "форма заполнена с ошибкми");
            return "registrationForme";
        }
        userServices.registrationUser(user);
        model.addAttribute("result", "Пользователь " +user.getName()+" добавлен");
        return "registrationForme";
    }
}
