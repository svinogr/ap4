package ap.controller;

import ap.dao.UserInfoDAO;
import ap.dao.WorkoutDAO;
import ap.entity.User;
import ap.entity.UserChangeData;
import ap.services.MailService;
import ap.services.TokenService;
import ap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    UserServices userServices;
    @Autowired
    WorkoutDAO workoutDAO;

    @Autowired
    UserInfoDAO userInfoDAO;
    @Autowired
    MailService mailService;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    TokenService tokenService;

 /*   @RequestMapping(value = "/registration", method = RequestMethod.GET)
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
            model.addAttribute("result", "Форма заполнена с ошибками");
            return "registrationForme";
        }

        try {
            userServices.registrationUser(user);

        } catch (Exception e) {
            model.addAttribute("result", "Такой Login или Email уже используется, если вы забыли пароль воспользуйтесь востановление пароля");
            return "registrationForme";
        }
        model.addAttribute("result", "Пользователь " + user.getLogin() + " добавлен, для завершения регистрации на указанную Вами" +
                " почту отправлена ссылка для активации учетной записи");
        return "registrationForme";
    }


  @RequestMapping(value = "acceptRegistration", method = RequestMethod.GET, params = {"token"})
    @Transactional
    public String acceptRegistration(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String responceMessage = userServices.acceptRegistration(token);
        model.addAttribute("result", responceMessage);
        return "index";

    }

 @RequestMapping(value = "acceptRegistration", method = RequestMethod.GET, params = {"token"})
    @Transactional
    public String acceptRegistration(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        if(userServices.acceptRegistrationHTML(token)){
            return "acceptRegistration";
        }

        return "index";

    }*/



    @RequestMapping(value = "/api/v.1/user/acceptRegistration", method = RequestMethod.GET, params = {"token"})
    @Transactional
    public String acceptRegistration(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        if(userServices.acceptRegistrationHTML(token)){
            return "acceptRegistration";
        }
        return "index";

    }


    @RequestMapping(value = "/api/v.1/user/rememberPass", method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public void receivePass(@RequestBody UserChangeData UserChangeData, HttpServletResponse response) {
        //TODO обезапасится от многократного запроса пароля
        System.err.println(UserChangeData.getEmail());
        String email = UserChangeData.getEmail();
        System.out.println(email);
        User user = userServices.getByEmail(email);
        System.err.println(user);
        if (user != null) {
            String login = user.getLogin();
            String token = tokenService.createToken(login);
            mailService.sendForgetPass(email, "token=" + token);
            response.setStatus(200);
        } else response.setStatus(404);
    }

    @RequestMapping(value = "/api/v.1/user/acceptRememberPass", method = RequestMethod.GET, params = {"token"})
    @Transactional
    public String changePass(HttpServletRequest request, HttpServletResponse response, Model model) {
        String token = request.getParameter("token");
        String login = tokenService.loginUserByToken(token);
        if (login != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(login);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            tokenService.deleteToken(token);
            response.setStatus(200);
            return "rememberPass";

        } else {
            response.setStatus(401);
            return "index";
        }

    }


    @RequestMapping(value = "/api/v.1/user/changePass", method = RequestMethod.POST)
    @Transactional
    public void changePass(@RequestBody UserChangeData userChangeData, HttpServletResponse response) {
        //TODO обезапасится от многократного запроса пароля
        String password = userChangeData.getPassword();
        if (userServices.changePassword(password)) {
            response.setStatus(200);

        } else response.setStatus(404);
        SecurityContextHolder.clearContext();
    }

/*


    @RequestMapping(value = "/forgetPass", method = RequestMethod.GET)
    public String getForgetPassPage() {
        return "forgetPass";
    }

@RequestMapping(value = "/rememberPass", method = RequestMethod.GET, params = {"email"})
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
            mailService.sendForgetPass(email, "token=" + token);
            response.setStatus(200);
        } else response.setStatus(400);
    }


@RequestMapping(value = "acceptRememberPass", method = RequestMethod.GET, params = {"token"})
    @Transactional
    public ModelAndView changePass(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        String login = tokenService.loginUserByToken(token);
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("changePassword");
        if (login != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(login);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            user.setLogin(login);
            modelAndView.addObject("result", "Введите новый пароль");
            tokenService.deleteToken(token);
            return modelAndView;

        } else {
            modelAndView.addObject("result", "Пользователь с указнным email не найден, попробуйте зарегистрироваться или востановить пароль");
            return modelAndView;
        }

    }



    @RequestMapping(value = "/confidential/reset", method = RequestMethod.POST)
    @Transactional
    public ModelAndView pageChangePassword(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        String password = user.getPassword();
        String login = user.getLogin();
        if (userServices.changePassword(login, password)) {
            modelAndView.addObject("result", "Пароль успешно изменен");

        } else modelAndView.addObject("result", "Пароль не может быть изменен, выберите востановление пароля повторно");
        modelAndView.setViewName("changePassword");
        SecurityContextHolder.clearContext();
        return modelAndView;
    }





    @RequestMapping(value = "/instruction")
    public String getInstructionPage(){
        return "instruction";
    }*/

}
