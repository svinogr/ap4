package ap.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(HttpServletRequest request, HttpServletResponse response)
    {
        return "login";
    }
   @RequestMapping(value = "/loginstatus", method = RequestMethod.GET)
    public void getStatus(HttpServletRequest request, HttpServletResponse response){
       if(SecurityContextHolder.getContext().getAuthentication().getPrincipal()!= "anonymousUser"){
           response.setStatus(200);
           System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

       } else {response.setStatus(401);
       }
   }



}
