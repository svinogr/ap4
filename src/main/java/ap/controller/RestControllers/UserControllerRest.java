package ap.controller.RestControllers;

import ap.entity.User;
import ap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserControllerRest {
    @Autowired
    UserServices userServices;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    String getUser(@PathVariable int id, Model model) {
        User user = userServices.getById(id);
        return user.getName();
    }
}
