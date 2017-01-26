package ap.controller.RestControllers;

import ap.entity.EntityForXML.UserXML;
import ap.entity.EntityForXML.WorkoutXML;
import ap.entity.User;
import ap.services.CreateXMLService;
import ap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("*/user")
public class UserControllerRest {
    @Autowired
    UserServices userServices;

    @Autowired
    CreateXMLService createXMLService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"})
    @Transactional
    public
    @ResponseBody
    String getUser(@PathVariable int id, Model model) {
        User user = userServices.getById(id);
        User userXML = new User();
        userXML.setLogin(user.getLogin());
        userXML.setActive(user.isActive());
        userXML.setEmail(user.getEmail());
        userXML.setDateRegistration(user.getDateRegistration());
        userXML.setId(user.getId());
        return createXMLService.getUserXML(user).toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putUser(@PathVariable int id, @Valid User user) {

    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public
    @ResponseBody
    String createUser(@Valid User user, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Location", "/user/1");
        return "создан";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"})
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public
    @ResponseBody
    String getResultSearch(@RequestParam Map<String, String> searchParams) {
        StringWriter xml = new StringWriter();
        searchParams.remove("_");
        for (Map.Entry<String, String> p : searchParams.entrySet()) {
            System.err.println(p.getKey() + " " + p.getValue());
        }
        if (searchParams.size() < 2) {

            List list = userServices.getSearchUser(searchParams);
            if (list.size() == 0) {
                return null;
            } else {

                User user = (User) list.get(0);
                xml = createXMLService.getUserXML(user);
            }

            return xml.toString();
        }
        return null;

    }
}
