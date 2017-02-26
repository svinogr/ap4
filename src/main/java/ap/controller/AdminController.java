package ap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping(value = "/administration/admin")
    public String getAdminPage(){
        return "administration";
    }

    @RequestMapping(value = "/*")
    public String getAPage(){
        return "static/index";
    }/////

    @RequestMapping(value = "/administration/users")
    public String getAdminUserPage(){
        return "adminuser";
    }


    @RequestMapping(value = "/administration/post")
    public String getAdminPostPage(){
        return "adminpost";
    }

}
