package ap.controller;

import ap.dao.UserInfoDAO;
import ap.dao.WorkoutDAO;
import ap.entity.UploadImageException;
import ap.entity.User;
import ap.entity.UserInfo;
import ap.services.InfoUserService;
import ap.services.MailService;
import ap.services.TokenService;
import ap.services.UserServices;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

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
    @Autowired
    InfoUserService infoUserService;

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
            model.addAttribute("result", "Форма заполнена с ошибками");
            return "registrationForme";
        }

        try {
            userServices.registrationUser(user);

        } catch (Exception e) {
            model.addAttribute("result", "Такой Login или Email уже используется, если вы забыли пароль воспользуйтесь востановление пароля");
            return "registrationForme";
        }
        model.addAttribute("result", "Пользователь " + user.getName() + " добавлен, для завершения регистрации на указанную Вами" +
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
    @RequestMapping(value = "/infoUser", method = RequestMethod.GET, params = {"id"})
    @Transactional
    public String getInfoUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = userInfoDAO.getByLogin(request.getParameter("id"));
        model.addAttribute("author", userInfo);
        return "infoStats";
    }

    @RequestMapping(value = "confidential/myInfo", method = RequestMethod.GET)
    @Transactional
    public String getInfoLoginUser(Model model, HttpServletRequest request, HttpServletResponse response) {
        String loggedLogin = userServices.getLoggedUser().getLogin();
        UserInfo userInfo = userInfoDAO.getByLogin(loggedLogin);

        if (loggedLogin != null) {
            model.addAttribute("author", userInfo);
        }
        return "myInfoStats";
    }
    @RequestMapping(value = "confidential/myInfoForChange", method = RequestMethod.GET )
    @Transactional
    public String changeLoginUserStats(Model model, HttpServletRequest request, HttpServletResponse response) {
    String loggedLogin = userServices.getLoggedUser().getLogin();
    UserInfo userInfo = userInfoDAO.getByLogin(loggedLogin);

    if (loggedLogin != null) {
        model.addAttribute("author", userInfo);
    }
    return "myInfoForChange";
}



    @RequestMapping(value = "confidential/myInfoForChange", method = RequestMethod.POST )
    @Transactional
    public String changeLoginUserStats(@Valid @ModelAttribute("author") UserInfo userInfo, BindingResult bindingResult, @RequestParam(value = "file", required = false) MultipartFile image, Model model, HttpServletRequest request, HttpServletResponse response) {
        System.err.println(userInfo.toString());
        System.err.println(image.getSize());
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "error");
            model.addAttribute("result", "Форма заполнена с ошибками");
            return "myInfoForChange";
        }
        if (userInfo != null) {
            UserInfo updateUserInfo = userInfoDAO.getByLogin(userInfo.getLogin());
            updateUserInfo.setAge(userInfo.getAge());
            updateUserInfo.setWeight(userInfo.getWeight());
            updateUserInfo.setHeight(userInfo.getHeight());
            updateUserInfo.setExperience(userInfo.getExperience());
            try {
                if (!image.isEmpty()) {
                   String imageForBD= infoUserService.upload(image);
                    updateUserInfo.setImage(imageForBD);
                }
            } catch (UploadImageException e) {
                bindingResult.reject(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }

            userInfoDAO.update(updateUserInfo);
            model.addAttribute("author", updateUserInfo);
            model.addAttribute("result", "Изменение внесены");
        }
        return "myInfoForChange";
    }

}
