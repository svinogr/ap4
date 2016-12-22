package ap.controller;

import ap.dao.WorkoutDAO;
import ap.entity.User;
import ap.entity.Workout;
import ap.services.MailService;
import ap.services.TokenService;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    @Autowired
    MailService mailService;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    TokenService tokenService;

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

        try {
            userServices.registrationUser(user);

        } catch (Exception e) {
            model.addAttribute("result", "такой Login или Email уже зарегистрирован");
            return "registrationForme";
        }
        model.addAttribute("result", "Пользователь " + user.getName() + " для завершения регистрации на указанную Вами" +
                " почту отправлена ссылка для активации учетной записи");
        return "registrationForme";
    }

    @RequestMapping(value = "acceptRegistration", method = RequestMethod.GET, params = {"token"})
    @Transactional
    public String acceptRegistration(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String responceMessage = userServices.acceptRegistration(token);
        model.addAttribute("result", responceMessage);
        return "acceptRegistration";

    }

    @RequestMapping(value = "forgetPass", method = RequestMethod.GET)
    public String getForgetPassPage() {
        return "forgetPass";
    }

    @RequestMapping(value = "rememberPass", method = RequestMethod.GET, params = {"email"})
    @Transactional
    public void receivePass(HttpServletRequest request, HttpServletResponse response) {
        //TODO обезапасится от многократного запроса пароля
        String email = request.getParameter("email");
        System.out.println(email);
        User user = userServices.getByEmail(email);
        System.err.println(user);
        if (user != null) {
            String login = user.getLogin();
            String token = tokenService.createToken(login);
            // mailService.sendForgetPass(email,"token="+token);
            response.setStatus(200);
        } else response.setStatus(400);
    }

    @RequestMapping(value = "acceptRememberPass", method = RequestMethod.GET, params = {"pass", "email"})
    @Transactional
    public ModelAndView changePass(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("changePass");
        String password = request.getParameter("pass");
        System.out.println(password);
        String email = request.getParameter("email");
        System.out.println(email);
        User user = userServices.getByEmail(email);
        if (user.getPassword().equals(password)) {
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }


    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    @Transactional
    public ModelAndView pageChangePassword(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("token", request.getParameter("token"));
        UserDetails userDetails = userDetailsService.loadUserByUsername("test");

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        modelAndView.addObject("user", new User());
        modelAndView.setViewName("changePassword");
        return modelAndView;
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String changePassword(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("token"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());

        return "lf";

    }


}
