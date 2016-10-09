package ap.controller;

import ap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.xml.parsers.DocumentBuilder;

@Controller
public class TestController {
    @Autowired
    UserDetailsService userDetailsService;

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
        creatTestXml();
        return modelAndView;
    }

    public void creatTestXml() {
        DocumentBuilder documentBuilder = null;
    }

    @RequestMapping(value = "/aut")
    public void aut() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getId());
    }
}
