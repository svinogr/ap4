package ap.controller;


import ap.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Controller
public class LoginController {

 @RequestMapping(value = "/login", method = RequestMethod.GET)
     public String getLoginPage(HttpServletRequest request, HttpServletResponse response)
     {
         return "login";
     }

    /**
     * @param request
     * @param response 200 if user is authenticated as user, 401 not authenticated
     */
    @RequestMapping(value = "/loginstatus", method = RequestMethod.GET)
    public void getStatus(HttpServletRequest request, HttpServletResponse response) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            response.setStatus(200);
        } else {
            response.setStatus(401);
        }
    }

    /**
     * @param request
     * @param response 200 if user is authenticated as admin, 401 not authenticated
     */
    @RequestMapping(value = "/adminstatus", method = RequestMethod.GET)
    public void getAdminStatus(HttpServletRequest request, HttpServletResponse response) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority role : authorities) {
            if (role.getAuthority().equals(Role.ROLE_ADMIN.toString())) {
                response.setStatus(200);
                break;
            } else {
                response.setStatus(401);
            }

        }
    }


}
