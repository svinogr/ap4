package ap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    public ModelAndView getTestPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        System.err.println("вызов тест контроллер");
        creatTestXml();
        return modelAndView;
    }

    public void creatTestXml()  {
        DocumentBuilder documentBuilder = null;

    }
    @RequestMapping(value = "/aut")
    public  void aut(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

           // System.out.println( userDetailsService.loadUserByUsername(user.getUsername() ));



       /* System.out.println(authentication.getPrincipal().toString());
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getDetails());
        System.out.println(authentication.isAuthenticated());*/


    }

}
