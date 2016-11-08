package ap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println(request.getContextPath());
        System.out.println(request.getPathInfo());
        System.out.println(request.getRemoteUser());
        System.out.println(request.getUserPrincipal());

        return "login";
    }
}
