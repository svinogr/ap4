package ap.controller.RestControllers;

import ap.entity.EntityForXML.TryXML;
import ap.entity.User;
import ap.services.TryService;
import ap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/v.1/try")
public class TryControllerRest {
    @Autowired
    TryService tryService;
    @Autowired
    UserServices userServices;

    /**
     * @param id       id in BD
     * @param response 404 try is not found for this authenticated user
     * @return tryXML
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    TryXML getTryById(@PathVariable int id, HttpServletResponse response) {
        TryXML tryXML = tryService.getTry(id);
        if (tryXML != null) {
            response.setStatus(200);
            return tryXML;
        }
        response.setStatus(404);
        return null;
    }

    /**
     * @param id       id in BD
     * @param response 401 user is not authenticated
     *                 404 exercise is not found for this user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public
    @Transactional
    @ResponseBody
    void deleteTryById(@PathVariable int id, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user == null) {
            response.setStatus(401);
            return;
        }
        if (tryService.deleteTry(id)) {
            response.setStatus(200);
        } else response.setStatus(404);
    }


    /**
     * @param tryXML tryXML. fields (weight and repeat are not be empty). if fild has value -1, field wont changed
     * @param id  id of try in BD
     * @param response 401 user is not authenticated
     *                 400  tryXML has error
     *                 404 try is not found for this user
     * @return tryXNL
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Transactional
    public
    @ResponseBody
    TryXML changeTryById(@RequestBody TryXML tryXML,@PathVariable int id, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user == null) {
            response.setStatus(401);
            return null;
        }
        tryXML = tryService.validTryXML(tryXML);
        if(tryXML.getWeight()==0 | tryXML.getRepeat()==0){
            response.setStatus(400);
            return tryXML;
        }
        tryXML.setTryId(id);
        if(tryService.changeTry(tryXML)){
            response.setStatus(200);
            return tryXML;
        }
        response.setStatus(404);
        return tryXML;
    }
    @RequestMapping(value = "/{id}/do", method = RequestMethod.PUT)
    @Transactional
    public
    @ResponseBody
    TryXML doTryById(@RequestBody TryXML tryXML,@PathVariable int id, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user == null) {
            response.setStatus(401);
            return null;
        }
        tryXML = tryService.validDoTryXML(tryXML);
        if(tryXML==null){
            response.setStatus(400);
            return tryXML;
        }
        tryXML.setTryId(id);
        if(tryService.done(tryXML)){
            response.setStatus(200);
            return tryXML;
        }
        response.setStatus(404);
        return tryXML;

    }
}
